using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BST
{
    public class Program
    {
        static void Main(string[] args)
        {
            var tree = new Tree();
            tree.Add(5);
            tree.Add(6);
            tree.Add(3);
            tree.Add(7);
            tree.Add(-1);

            foreach (int n in tree)
            {
                Console.WriteLine(n);
            }
        }
    }
}
