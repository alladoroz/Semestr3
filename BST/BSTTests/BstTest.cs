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
            tree.Remove(1);
            Assert.IsTrue(tree.Search(1));
        }

        [TestMethod]
        public void TestAdd()
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

        [TestMethod]
        public void DeleteTest1()
        {
            tree.Add(3);
            tree.Add(24);
            tree.Add(11);
            tree.Add(10);
            tree.Add(19);
            tree.Add(18);
            tree.Remove(19);
            tree.Remove(24);
            tree.Remove(10);
            Assert.IsFalse(tree.Search(24));
            Assert.IsFalse(tree.Search(10));
            Assert.IsFalse(tree.Search(19));
        }

        [TestMethod]
        public void DeleteTest2()
        {
            tree.Add(8);
            tree.Add(4);
            tree.Add(2);
            tree.Add(6);
            tree.Add(1);
            tree.Add(3);
            tree.Add(5);
            tree.Add(7);
            tree.Add(12);
            tree.Add(10);
            tree.Add(14);
            tree.Add(9);
            tree.Add(11);
            tree.Add(13);
            tree.Add(15);
            tree.Remove(8);
            tree.Remove(11);
            Assert.IsFalse(tree.Search(11));
        }

        [TestMethod]
        public void TestIsEmpty()
        {
            Assert.IsTrue(tree.IsEmpty());
            tree.Add(6);
            Assert.IsFalse(tree.IsEmpty());
        }
    }
}
