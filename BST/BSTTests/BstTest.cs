using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using BST;

namespace BSTTests
{
    [TestClass]
    public class BstTest
    {
        Tree tree = new Tree();

        [TestMethod]
        public void TestAdd()
        {
            tree.Add(7);
            tree.Add(8);
            tree.Add(43);
            tree.Print();
        }

        [TestMethod]
        public void TestSearch()
        {
            tree.Add(5);
            tree.Add(4);
            Assert.IsTrue(tree.Search(5));
            Assert.IsFalse(tree.Search(100500));
        }

        [TestMethod]
        public void DeleteTest()
        {
            tree.Add(1);
            tree.Add(1);
            tree.Delete(1);
            Assert.IsTrue(tree.Search(1));
        }

        [TestMethod]
        public void TestAdd1()
        {
            tree.Add(5);
            tree.Add(6);
            tree.Add(3);
            tree.Add(7);
            tree.Add(-1);
            int count = 0;
            int[] arr = { -1, 3, 5, 6, 7 };
            foreach (int item in tree)
            {
                Assert.AreEqual(arr[count], item);
                count++;
            }
        }
    }
}
