package prototype

import Comparator.Comparator
import java.io.InputStream

interface ProtoType {
    // Имя типа
    fun typeName(): String?

    // Создание объекта
    fun create(): Any?

    // Клонирование текущего
    fun clone(obj: Any?): Any?

    // Создание и чтения объекта
    fun readValue(inputStream: InputStream?): Any?

    // Создает и парсит содержимое из строки
    fun parseValue(someString: String?): Any?

    // Возврат компаратора для сравнения
    val typeComparator: Comparator?
}