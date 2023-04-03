fun chapter911() {
    val root = TreeNode911("Hello").apply {
        addChild("World")
        addChild("!!!")
    }

    println(root) // Hello {World {}, !!! {}}
    println(root.depth) // 2
}

class TreeNode911<T>(val data: T) {
    private val _children = arrayListOf<TreeNode911<T>>()
    var parent: TreeNode911<T>? = null
        private set

    val children: List<TreeNode911<T>> get() = _children

    fun addChild(data: T) = TreeNode911(data).also {
        _children += it
        it.parent = this
    }

    override fun toString() = _children.joinToString(prefix = "$data {", postfix = "}")
}

val <T> TreeNode911<T>.depth: Int
    get() = (children.asSequence().map { it.depth }.maxOrNull() ?: 0) + 1