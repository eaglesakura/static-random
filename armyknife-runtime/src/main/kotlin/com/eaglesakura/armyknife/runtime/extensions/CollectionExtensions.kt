@file:Suppress("unused")

package com.eaglesakura.armyknife.runtime.extensions

/**
 * find "K" from map.
 *
 * e.g.)
 * val map = mapOf(
 *      Pair("Key", "Value")
 * )
 * val key = map.findKey { value ->
 *      value == "Value"
 * }
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <K, V> Map<K, V>.findKey(selector: (value: V) -> Boolean): K? {
    this.entries.forEach {
        if (selector(it.value)) {
            return it.key
        }
    }
    return null
}

/**
 * Returns true, if it was null or empty.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T> Collection<T>?.isNullOrEmpty(): Boolean {
    if (this == null) {
        return true
    }

    return isEmpty()
}

/**
 * An obj add to list when not overlaps.
 * This method returns added index or overlap object index.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T> MutableCollection<T>.addUnique(obj: T): Int {
    forEachIndexed { index, value ->
        if (value == obj) {
            return index
        }
    }
    add(obj)
    return size - 1
}

/**
 * An obj in list add to list when not overlaps.
 * This method returns added index or overlap object index.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T> MutableCollection<T>.addUniqueAll(list: Iterable<T>) {
    for (item in list) {
        addUnique(item)
    }
}

/**
 * Delete overlaps object in this list.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T> MutableCollection<T>.shrink() {
    val temp = mutableSetOf<T>()
    this.iterator().also { iterator ->
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (temp.contains(item)) {
                iterator.remove()
            } else {
                temp.add(item)
            }
        }
    }
}

/**
 *  List in List to simple List.
 *  e.g.)
 *      [[a, b, c], [d, e, f]] to [a, b, c, d, e, f]
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T> Iterable<Iterable<T>>.flatList(): List<T> {
    return flatList { it }
}

/**
 *  List in List to simple List.
 *  e.g.)
 *      [[a, b, c], [d, e, f]] to [a', b', c', d', e', f']
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T, R> Iterable<Iterable<T>>.flatList(transform: (T) -> R): List<R> {
    return flatListTo(ArrayList(), transform)
}

/**
 *  List in List to simple List.
 *  e.g.)
 *      [[a, b, c], [d, e, f]] to [a', b', c', d', e', f']
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun <T, R> Iterable<Iterable<T>>.flatListTo(dst: MutableList<R>, transform: (T) -> R): MutableList<R> {
    forEach { list ->
        list.forEach {
            dst.add(transform(it))
        }
    }
    return dst
}
