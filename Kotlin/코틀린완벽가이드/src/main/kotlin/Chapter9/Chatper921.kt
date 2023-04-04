fun chapter921() {
//    val node: TreeNode921<Any> = TreeNode921<String>("Hello") // invariant, error: type mismatch
    val list: List<Any> = List<String>(1) { "" } // covariant, not error
}

class TreeNode921<T>(val data: T) {
    private val _children = arrayListOf<TreeNode921<T>>()
    var parent: TreeNode921<T>? = null
        private set

    val children: List<TreeNode921<T>> get() = _children

    fun addChild(data: T) = TreeNode921(data).also {
        _children += it
        it.parent = this
    }

    override fun toString() = _children.joinToString(prefix = "$data {", postfix = "}")
}