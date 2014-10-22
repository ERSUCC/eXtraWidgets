package uk.ac.surrey.soc.cress.extrawidgets.slider

import java.awt.BorderLayout
import java.awt.BorderLayout.CENTER
import java.awt.BorderLayout.LINE_END
import java.awt.BorderLayout.PAGE_START
import org.nlogo.window.GUIWorkspace
import org.nlogo.window.InterfaceColors.SLIDER_BACKGROUND
import javax.swing.BorderFactory.createEmptyBorder
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSlider
import javax.swing.SwingConstants
import uk.ac.surrey.soc.cress.extrawidgets.api.IntegerPropertyDef
import uk.ac.surrey.soc.cress.extrawidgets.api.JComponentWidget
import uk.ac.surrey.soc.cress.extrawidgets.api.StateUpdater
import uk.ac.surrey.soc.cress.extrawidgets.api.StringPropertyDef
import uk.ac.surrey.soc.cress.extrawidgets.api.WidgetKey
import uk.ac.surrey.soc.cress.extrawidgets.api.const
import uk.ac.surrey.soc.cress.extrawidgets.api.swing.enrichSlider

class Slider(
  val key: WidgetKey,
  val stateUpdater: StateUpdater,
  ws: GUIWorkspace)
  extends JPanel
  with JComponentWidget {

  setHeight(50)

  setLayout(new BorderLayout())
  setBackground(SLIDER_BACKGROUND)

  override def borderPadding = createEmptyBorder(0, 4, 0, 4)

  val slider = new JSlider()
  val textLabel = new JLabel(key)
  val valueLabel = new JLabel(slider.getValue.toString)
  add(slider, PAGE_START)
  add(textLabel, CENTER)
  add(valueLabel, LINE_END)

  val xwText = new StringPropertyDef(this,
    textLabel.setText,
    textLabel.getText)

  val xwMinimum = new IntegerPropertyDef(this,
    slider.setMinimum(_),
    slider.getMinimum)

  val xwMaximum = new IntegerPropertyDef(this,
    slider.setMaximum(_),
    slider.getMaximum)

  val xwValue = new IntegerPropertyDef(this,
    slider.setValue(_),
    slider.getValue)

  slider.onStateChange { _ ⇒
    valueLabel.setText(xwValue.stringValue)
    xwValue.updateInState()
  }

}
