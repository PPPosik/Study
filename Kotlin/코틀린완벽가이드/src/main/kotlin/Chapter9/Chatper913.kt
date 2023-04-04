fun chapter913() {
    val tree = TreeNode913<Any>("abc").addChild("def").addChild(123)

    println(tree.isInstanceOf<String>())
}

class TreeNode913<T>(val data: T) {
    private val _children = arrayListOf<TreeNode913<T>>()
    var parent: TreeNode913<T>? = null
        private set

    val children: List<TreeNode913<T>> get() = _children

    fun addChild(data: T) = TreeNode913(data).also {
        _children += it
        it.parent = this
    }

    override fun toString() = _children.joinToString(prefix = "$data {", postfix = "}")
}

fun <T> TreeNode913<T>.cancellableWalkDepthFirst(
    onEach: (T) -> Boolean,
): Boolean {
    val nodes = java.util.LinkedList<TreeNode913<T>>()

    nodes.push(this)

    while (nodes.isNotEmpty()) {
        val node = nodes.pop()
        if (!onEach(node.data)) return false
        node.children.forEach { nodes.push(it) }
    }

    return true
}

inline fun <reified T> TreeNode913<*>.isInstanceOf() = cancellableWalkDepthFirst { it is T }