package uk.ac.surrey.xw.api

import java.awt.BorderLayout
import java.awt.Color.white

import javax.swing.JPanel
import javax.swing.JScrollPane

import org.nlogo.app.App
import org.nlogo.window.GUIWorkspace

import uk.ac.surrey.xw.api.RichWorkspace.enrichWorkspace

class TabKind[W <: Tab] extends WidgetKind[W] {
  val newWidget = new Tab(_, _, _)
  val name = "TAB"
  val defaultProperty = None
  val colorProperty = new ColorProperty[W](
    "COLOR", Some(_.panel.setBackground(_)), _.panel.getBackground, white)
  val titleProperty = new StringProperty[W](
    "TITLE", Some(_.setTitle(_)), _.getTitle)
  val enabledProperty = new BooleanProperty[W](
    "ENABLED", Some(_.setEnabled(_)), _.isEnabled, true)
  val orderProperty = new DoubleProperty[W](
    "ORDER", Some(_.setOrder(_)), _.getOrder, 0d
  )
  override def propertySet = super.propertySet ++ Set(
    titleProperty, colorProperty,
    enabledProperty, orderProperty
  )
}

class Tab(
  val key: WidgetKey,
  val state: State,
  val ws: GUIWorkspace)
  extends JPanel
  with ExtraWidget {

  val kind = new TabKind[this.type]

  private var _order = 0d
  def setOrder(order: Double) = {
    _order = order
    ws.reorderTabs(state)
  }
  def getOrder = _order

  override def isOptimizedDrawingEnabled = false

  val tabManager = ws.tabManager
  val tabsMenu = App.app.menuBar.tabsMenu

  setOpaque(true)

  val panel = new JPanel {
    setLayout(null)
    override def getPreferredSize: java.awt.Dimension =
      if (getComponents.nonEmpty) {
        val maxX = getComponents.map(c ⇒ c.getLocation.x + c.getSize.width).max
        val maxY = getComponents.map(c ⇒ c.getLocation.y + c.getSize.height).max
        new java.awt.Dimension(maxX, maxY)
      } else new java.awt.Dimension(0, 0)
  }
  setLayout(new BorderLayout)
  add(new JScrollPane(panel), BorderLayout.CENTER)

  addToAppTabs()

  private var _title = ""
  def setTitle(title: String): Unit = {
    _title = title
    if (!tabManager.setTabTitle(this, title))
      throw XWException("Tab " + key + " not in application tabs.")
  }
  def getTitle: String = _title

  def addToAppTabs(): Unit =
    (0 until tabManager.getTotalTabCount)
      .find { i ⇒
        tabManager.getTabAt(i) match {
          case _: org.nlogo.app.interfacetab.InterfaceTab ⇒ false
          case _: Tab ⇒ false
          case _ ⇒ true
        }
      }
      .foreach { i ⇒
        tabManager.insertTab(this, _title, i)
        tabManager.updateTabActions()
      }

  def removeFromAppTabs(): Unit = {
    tabManager.removeTab(this)
  }

}
