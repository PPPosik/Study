fun chapter912() {
    val root = TreeNode912(1).apply {
        addChild(2).addChild(3)
        addChild(4).addChild(5)
    }

    println(root.average()) // 3.0
    println(root.maxNode()) // 4 {5 {}}

    val list = ArrayList<Number>()
    root.toList(list)
    println(list) // [3, 2, 5, 4, 1]
}

class TreeNode912<T>(val data: T) {
    private val _children = arrayListOf<TreeNode912<T>>()
    var parent: TreeNode912<T>? = null
        private set

    val children: List<TreeNode912<T>> get() = _children

    fun addChild(data: T) = TreeNode912(data).also {
        _children += it
        it.parent = this
    }

    override fun toString() = _children.joinToString(prefix = "$data {", postfix = "}")
}

fun <T> TreeNode912<T>.walkDepthFirst(action: (T) -> Unit) {
    children.forEach { it.walkDepthFirst(action) }
    action(data)
}

fun <T : Number> TreeNode912<T>.average(): Double {
    var count = 0
    var sum = 0.0

    walkDepthFirst {
        count++
        sum += it.toDouble()
    }

    return sum / count
}

fun <T : Comparable<T>> TreeNode912<T>.maxNode(): TreeNode912<T> {
    val maxChild = children.maxByOrNull { it.data } ?: return this

    return if (data >= maxChild.data) this else maxChild
}

fun <T, U : T> TreeNode912<U>.toList(list: MutableList<T>) {
    walkDepthFirst { list += it }
}