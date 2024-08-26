package uk.ac.surrey.xw.api

import scala.language.implicitConversions

import org.nlogo.app.App
import org.nlogo.app.AppFrame
import org.nlogo.app.TabManager
import org.nlogo.window.GUIWorkspace

object RichWorkspace {
  implicit def enrichWorkspace(ws: GUIWorkspace) =
    new RichWorkspace(ws)
}

class RichWorkspace(ws: GUIWorkspace) {

  def tabManager: TabManager = ws.getFrame.asInstanceOf[AppFrame].getLinkChildren
    .collectFirst { case app: App ⇒ app.tabManager }
    .getOrElse(throw new XWException("Can't access application tab manager."))

  def xwTabs = tabManager.getTabs.collect { case t: Tab ⇒ t }

  def reorderTabs(state: State): Unit =
    for (tab ← xwTabs.sortBy(t ⇒ (t.getOrder, state.tabCreationOrder(t.key)))) {
      tabManager.removeTab(tab)
      tab.addToAppTabs()
    }
}
