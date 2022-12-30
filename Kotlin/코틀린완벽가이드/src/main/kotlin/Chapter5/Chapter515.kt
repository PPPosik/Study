fun chapter515() {
    println(indexOf(intArrayOf(4, 3, 2, 1)) { it < 3 }) // 2
}

inline fun indexOf(numbers: IntArray, condition: (Int) -> Boolean): Int {
    for (i in numbers.indices) {
        if (condition(numbers[i])) {
            return i
        }
    }

    return -1
}

/*
* same as
*
* val numbers = intArrayOf(4, 3, 2, 1)
* val index = -1
*
* for (i in numbers.indices) {
*   if (numbers[i] < 3) {
*       index = i
*       break
*   }
* }
*
* println(index)
*
* */

// nullable -> noinline
inline fun forEach(a: IntArray, noinline action: ((Int) -> Unit)?) {
    if (action == null) return
    for (n in a) action(n)
}