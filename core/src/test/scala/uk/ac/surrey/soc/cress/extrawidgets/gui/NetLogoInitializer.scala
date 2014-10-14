package uk.ac.surrey.soc.cress.extrawidgets.gui

import org.nlogo.app.App
import org.nlogo.app.ToolsMenu
import akka.dispatch.Await
import akka.dispatch.Promise
import akka.util.duration.intToDurationInt
import javax.swing.JFrame
import uk.ac.surrey.soc.cress.extrawidgets.gui.Swing.enrichComponent
import collection.JavaConverters._
import org.nlogo.api.ModelType._
import org.nlogo.window.GUIWorkspace

object NetLogoInitializer {

  import SwingExecutionContext.swingExecutionContext

  App.main(Array[String]())
  val wsPromise = Promise[GUIWorkspace]()
  App.app.frame.onComponentShown { _ ⇒
    println(App.app.workspace)
    wsPromise.success(App.app.workspace)
  }
  lazy val gui: GUI = {
    val ws = Await.result(wsPromise, 10 seconds)
    val frame = App.app.frame
    ws.open("test.nlogo")
    val jMenuBar = frame.getJMenuBar
    val toolsMenu = (0 until jMenuBar.getMenuCount)
      .map(jMenuBar.getMenu)
      .collectFirst { case m: ToolsMenu ⇒ m }
      .getOrElse(throw new Exception("Can't find tools menu."))
    val createTabMenuItem = (0 until toolsMenu.getItemCount)
      .map(toolsMenu.getItem)
      .collectFirst { case m: CreateTabMenuItem ⇒ m }
      .getOrElse(throw new Exception("Can't find CreateTab menu item."))
    createTabMenuItem.gui
  }
}
