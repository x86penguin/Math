/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 x86penguin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public final class MathCalculations extends JFrame
{
    private JButton lcmButton = new JButton("LCM");

    public MathCalculations()
    {
        super("Math Calculations");
        setLayout(new GridLayout(3,3));

        add(lcmButton);

        LCMHandler lcmHandler = new LCMHandler();
        lcmButton.addActionListener(lcmHandler);
    }

    public class LCMHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            LCMWindow w = new LCMWindow();
            w.setSize(350,200);
            w.setVisible(true);
            w.setLocationRelativeTo(null);
        }
    }

    private class LCMWindow extends JFrame
    {
        private JTextField factor1 = new JTextField();
        private JTextField factor2 = new JTextField();
        private JButton button = new JButton("Find lowest common multiple");
        private JTextField output = new JTextField();

        public LCMWindow()
        {
            super("Lowest Common Multiple");
            setLayout(new GridLayout(4,1));

            output.setEditable(false);

            add(factor1);
            add(factor2);
            add(output);
            add(button);

            MathHandler mathHandler = new MathHandler();
            button.addActionListener(mathHandler);
        }

        private class MathHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                double number1 = Double.parseDouble(factor1.getText());
                double number2 = Double.parseDouble(factor2.getText());
                double[] products1 = new double[10000];
                double[] products2 = new double[10000];
                double multiplier1 = 1;
                double multiplier2 = 1;
                double lcm = 0;
                int index = 0;
                mainloop:
                while (true)
                {
                    products1[index] = multiplier1 * number1;
                    products2[index] = multiplier2 * number2;
                    for (int i = 0; i <= index; i++)
                    {
                        for (int i2 = 0; i2 <= index; i2++)
                        {
                            if (products1[i] == products2[i2])
                            {
                                lcm = products1[i];
                                break mainloop;
                            }
                        }
                    }
                    multiplier1++;
                    multiplier2++;
                    index++;
                }
                output.setText(""+lcm);
            }
        }
    }

    public static void main(String[] args)
    {
        MathCalculations w = new MathCalculations();
        w.setSize(500,500);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);
        w.setLocationRelativeTo(null);
    }
}
