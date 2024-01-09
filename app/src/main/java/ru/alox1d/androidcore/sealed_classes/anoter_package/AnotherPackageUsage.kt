package sealed_classes.anoter_package

import sealed_classes.CustomError

//open class CustomError(): Error // ERROR, wrong package
open class AnotherCustomError() : CustomError() // Possible
