using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Robots;

namespace RobotsTests
{
    [TestClass]
    public class RobotsTest
    {
        [TestMethod]
        public void TestDestruct()
        {
            byte[,] graph = 
            {
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0},
            };
            byte[] robots = { 1, 1, 0, 0, 0, 1 };

            RoboNet net = new RoboNet(graph, robots);
            Assert.IsTrue(net.Destruct());
        }

        [TestMethod]
        public void TestNotDestruct()
        {
            byte[,] graph = 
            {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
            };
            byte[] robots = { 1, 1, 1 };

            RoboNet net = new RoboNet(graph, robots);
            Assert.IsFalse(net.Destruct());
        }
    }
}
