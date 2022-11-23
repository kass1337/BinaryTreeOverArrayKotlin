package view

import factory.*
import prototype.*
import structure.*

import javax.swing.JPanel
import java.awt.event.ActionListener
import factory.FactoryType
import structure.BinaryTreeArray
import prototype.ProtoType
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.JTextField
import javax.swing.JComboBox
import javax.swing.JFrame
import view.GUI
import java.awt.event.ActionEvent
import javax.swing.JOptionPane
import java.awt.Dimension
import java.awt.Font

class GUI : JPanel(), ActionListener {
    var factoryType: FactoryType
    var btsArray: BinaryTreeArray?
    var protoType: ProtoType?
    private val addElem: JButton
    private val deleteElem: JButton
    private val saveBinary: JButton
    private val loadBinary: JButton
    private val findByIndex: JButton
    private val balance: JButton
    private val mainText: JTextArea
    private val deleteTextField: JTextField
    private val findTextField: JTextField
    private val factoryList: JComboBox<*>
    private var selectedType = "Integer"
    fun showGui() {
        val frame = JFrame("KiTPO. LR #1")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(GUI())
        frame.pack()
        frame.isVisible = true
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === factoryList) {
            val box = e.source as JComboBox<*>
            val item = box.selectedItem as String
            if (selectedType !== item) {
                selectedType = item
                protoType = factoryType.getBuilderByName(selectedType)
                btsArray = BinaryTreeArray(protoType!!.typeComparator)
                rewrite()
            }
        }
        // обработчик добавления элемента
        if (e.source === addElem) {
            btsArray!!.addValue(protoType!!.create()!!)
            rewrite()
        }

        // обработчик удаления элемента
        if (e.source === deleteElem) {
            if (deleteTextField.text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введите значение!")
                return
            }
            btsArray!!.removeNodeByIndex(deleteTextField.text.toInt())
            rewrite()
        }

        // обработчик поиска элемента по индексу
        if (e.source === findByIndex) {
            if (findTextField.text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введите значение!")
                return
            }
            val findValue = btsArray!!.getDataAtIndex(findTextField.text.toInt()).toString()
            JOptionPane.showMessageDialog(
                null, """
     Значение = $findValue
     по индексу = ${findTextField.text}
     """.trimIndent()
            )
        }

        // обработчик балансировки
        if (e.source === balance) {
            btsArray = btsArray!!.balance()
            rewrite()
        }

        // обработчик сохранения
        if (e.source === saveBinary) {
            btsArray!!.save(protoType)
        }

        // обработчик загрузки
        if (e.source === loadBinary) {
            btsArray = btsArray!!.load(protoType)
            rewrite()
        }
    }

    private fun rewrite() {
        mainText.text = btsArray.toString()
    }

    init {
        factoryType = FactoryType()
        protoType = factoryType.getBuilderByName("Integer")
        btsArray = BinaryTreeArray(protoType!!.typeComparator)

        //construct preComponent
        val typeNameList = factoryType.getTypeNameList()
        val factoryListItems = arrayOfNulls<String>(typeNameList.size)
        for (i in typeNameList.indices) {
            factoryListItems[i] = typeNameList[i]
        }

        //construct components
        addElem = JButton("Добавить элемент")
        deleteElem = JButton("Удалить элемент по индексу")
        saveBinary = JButton("Сохранить в двоичный")
        loadBinary = JButton("Загрузить из двоичного")
        findByIndex = JButton("Поиск по индексу")
        balance = JButton("Балансировка")
        mainText = JTextArea(5, 5)
        deleteTextField = JTextField(5)
        findTextField = JTextField(5)
        factoryList = JComboBox<Any?>(factoryListItems)

        //adjust size and set layout
        preferredSize = Dimension(908, 577)
        layout = null

        //add components
        add(addElem)
        add(deleteElem)
        add(saveBinary)
        add(loadBinary)
        add(findByIndex)
        add(balance)
        add(mainText)
        add(deleteTextField)
        add(findTextField)
        add(factoryList)

        //set component bounds (only needed by Absolute Positioning)
        addElem.setBounds(720, 55, 150, 45)
        deleteElem.setBounds(690, 110, 205, 25)
        deleteTextField.setBounds(745, 145, 100, 25)
        findByIndex.setBounds(710, 180, 170, 25)
        findTextField.setBounds(745, 215, 100, 25)
        balance.setBounds(705, 250, 180, 30)
        saveBinary.setBounds(690, 340, 205, 35)
        loadBinary.setBounds(690, 395, 205, 35)
        mainText.setBounds(25, 55, 645, 485)
        factoryList.setBounds(25, 15, 100, 25)
        addElem.addActionListener(this)
        deleteElem.addActionListener(this)
        saveBinary.addActionListener(this)
        loadBinary.addActionListener(this)
        findByIndex.addActionListener(this)
        factoryList.addActionListener(this)
        balance.addActionListener(this)
        mainText.isEnabled = true
        mainText.font = Font("Arial", Font.BOLD, 14)
    }
}