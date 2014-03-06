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

        public void TestSearch()
        {
            tree.Add(5);
            tree.Add(4);
            Assert.IsTrue(tree.Search(5));
            Assert.IsFalse(tree.Search(100500));
        }
    }
}
