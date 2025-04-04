package vcmsa.ci.financetracker



import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etIncome: EditText
    private lateinit var etExp1: EditText
    private lateinit var etExp2: EditText
    private lateinit var etExp3: EditText
    private lateinit var etExp4: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvIncome: TextView
    private lateinit var tvExpense: TextView
    private lateinit var tvBalance: TextView
    private lateinit var tvAnalysis: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etIncome = findViewById(R.id.etIncome)
        etExp1 = findViewById(R.id.etExp1)
        etExp2 = findViewById(R.id.etExp2)
        etExp3 = findViewById(R.id.etExp3)
        etExp4 = findViewById(R.id.etExp4)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvIncome = findViewById(R.id.tvIncome)
        tvExpense = findViewById(R.id.tvExpense)
        tvBalance = findViewById(R.id.tvBalance)
        tvAnalysis = findViewById(R.id.tvAnalysis)

        btnCalculate.setOnClickListener { actCalculate() }

    }

    private fun actCalculate() {
        val strIncome = etIncome.text.toString().trim()
        val intIncome = if (strIncome.isNotEmpty()) strIncome.toInt() else 0


        if (intIncome <= 0) {
            Toast.makeText(
                this,
                getString(R.string.please_enter_your_income_amount),
                Toast.LENGTH_SHORT
            ).show()
            return

        }

        val num1 = etExp1.text.toString().toIntOrNull() ?: 0
        val num2 = etExp2.text.toString().toIntOrNull() ?: 0
        val num3 = etExp3.text.toString().toIntOrNull() ?: 0
        val num4 = etExp4.text.toString().toIntOrNull() ?: 0


        if (num1 == 0) {
            Toast.makeText(this, "Please enter your food Expense Amount", Toast.LENGTH_SHORT).show()
            return

        } else if (num2 == 0) {
            Toast.makeText(
                this,
                getString(R.string.please_enter_your_second_expense_amount),
                Toast.LENGTH_SHORT
            ).show()
            return

        } else if (num3 == 0) {
            Toast.makeText(
                this,
                getString(R.string.please_enter_your_third_expense_amount),
                Toast.LENGTH_SHORT
            ).show()
            return

        } else if (num4 == 0) {
            Toast.makeText(
                this,
                getString(R.string.please_enter_your_last),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

            val result: Int = num1 + num2 + num3 + num4
            tvExpense.text = "$result"

            val income = etIncome.text.toString().toInt()

            val balance = income - result
            tvBalance.text = "$balance"


            val expensePercentage = (result.toFloat() / intIncome.toFloat()) * 100

            if (expensePercentage > 30) {
                tvAnalysis.text =
                    (getString(R.string.warning_your_expenses_are_too_high_consider_cutting_back))
                tvAnalysis.setTextColor(Color.RED)

            } else if (expensePercentage < 5) {
                tvAnalysis.text = (getString(R.string.great_you_re_keeping_expenses_low))
                tvAnalysis.setTextColor(Color.GREEN)

            } else {
                tvAnalysis.text =
                    (getString(R.string.your_expenses_are_within_a_reasonable_range))
                tvAnalysis.setTextColor(Color.BLACK)
            }

        }
    }














