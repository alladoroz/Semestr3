using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using NullElementsCounter;

namespace NullElementsCounterTests
{
    [TestClass]
    public class NullElementsCounterTest
    {
        [TestMethod]
        public void TestCounter()
        {
            var array = new int[] { 1, 9, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 8, 4, 5 };
            Assert.AreEqual(7, NullElementsCounter.Counter.LambdaCounter(array));
        }
    }
}
