package abhi.learn.java.ss;

/**
 * Created by Abhishek on 9/24/2016.
 */
public class IntegerBox {
    private int number;
    private boolean checked = false;
    private boolean isPrime = false;

    public IntegerBox(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    @Override
    public String toString() {
        return "IntegerBox{" +
                "number=" + number +
                ", checked=" + checked +
                ", isPrime=" + isPrime +
                '}';
    }
}
