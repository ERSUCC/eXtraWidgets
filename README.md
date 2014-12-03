![eXtraWidgets](doc/eXtraWidgets.png)

An extension for creating additional interface tabs in the [NetLogo](https://github.com/NetLogo/NetLogo) GUI and putting custom widgets on them.

### Installing

To install the eXtraWidgets extension, assuming that you already have [NetLogo](https://ccl.northwestern.edu/netlogo/download.shtml) installed, [**download** the extension](https://github.com/nicolaspayette/eXtraWidgets/releases/latest) and unzip it NetLogo's `extensions/` folder, like you would for any other [NetLogo extension](http://ccl.northwestern.edu/netlogo/docs/extensions.html).

The extension was developed and tested with NetLogo 5.1. There is a good chance that it will work with other versions of NetLogo >= 5.0, but we have not checked.

### Using

### Extending

This is what we believe to be the first "extensible extension" for NetLogo. New widgets kinds can be added to the extension just by dropping a JAR in a folder under `xw/widgets`. [Developer documentation](https://github.com/nicolaspayette/eXtraWidgets/wiki/Developing-Extra-Widget-Kinds) is scarce at the moment, so your best bet is probably to [take a look at the source code of existing widgets](https://github.com/nicolaspayette/eXtraWidgets/tree/master/xw/widgets). The [ScalaDoc for the API](https://nicolaspayette.github.io/eXtraWidgets/) is also a good ressource.

If you encounter any difficulties, either [open a new issue](https://github.com/nicolaspayette/eXtraWidgets/issues/new) or [ask a question on StackOverflow](http://stackoverflow.com/questions/tagged/netlogo).

### Licensing

The extension was developed by [Nicolas Payette](https://github.com/nicolaspayette) at the [Centre for Research in Social Simulation (CRESS)](http://cress.soc.surrey.ac.uk/), under the supervision of [Nigel Gilbert](http://cress.soc.surrey.ac.uk/web/people/director-cress) and [Jen Badham](http://cress.soc.surrey.ac.uk/web/people/researchers/76-jen-Badham). Its first use case was for a model that is part of the [TELL ME project](http://www.tellmeproject.eu/).

The extension is distributed under the [MIT License](LICENSE.txt).

[NetLogo](http://ccl.northwestern.edu) itself is authored by [Uri Wilensky](http://ccl.northwestern.edu/uri/) and [distributed under the GPL](http://ccl.northwestern.edu/netlogo/docs/copyright.html).

---
[![build status](https://magnum.travis-ci.com/nicolaspayette/eXtraWidgets.svg?token=SMhSmaihoAiHTwEPS2Xp)](https://magnum.travis-ci.com/nicolaspayette/eXtraWidgets)
