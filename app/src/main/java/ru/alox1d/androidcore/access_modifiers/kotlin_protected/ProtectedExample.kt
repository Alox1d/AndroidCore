package access_modifiers.kotlin_protected

class ProtectedExample {
    protected val s1 = "s1"
    private val s2 = "s1"

    fun foo() {
        // Cannot access
        // this.Usage().s3
        // this.Usage().s4

        this.Usage().s5
    }

    inner class Usage {
        protected val s3 = "s1"
        private val s4 = "s1"
        val s5 = "s1"

        fun foo() {
            val p = ProtectedExample()
            // Cannot access 's1': it is protected in 'ProtectedExample'
            p.s1
            p.s2
        }
    }
}

class Usage {
    fun foo() {
        val p = ProtectedExample()

        // Cannot access 's1': it is protected in 'ProtectedExample'
        // p.s1
    }
}
