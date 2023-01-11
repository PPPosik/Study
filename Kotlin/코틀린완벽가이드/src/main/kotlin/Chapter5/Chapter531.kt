fun chapter531() {
    println((1..3).leftHalf) // 1..2
    println((3..6).leftHalf) // 3..4

    val numbers = IntArray(6) { it * it } // [0, 1, 4, 9, 16, 25]
    println(numbers.midValue) // 4
    numbers.midValue *= 10
    println(numbers.midValue) // 40
}

val IntRange.leftHalf: IntRange
    get() = start..(start + endInclusive) / 2

val IntArray.midIndex
    get() = lastIndex / 2

var IntArray.midValue
    get() = this[midIndex]
    set(value) {
        this[midIndex] = value
    }