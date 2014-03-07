using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Robots
{
    /// <summary>
    /// Class used to find out if set of robots can be destroyed
    /// </summary>
    public class RoboNet
    {
        /// <summary>
        /// Graph constructor for robots
        /// </summary>
        /// <param name="graph"></param>
        /// <param name="robots"></param>
        public RoboNet(byte[,] graph, byte[] robots)
        {
            this.graph = graph;
            this.robots = robots;
            this.whiteGraph = new ArrayList();
            this.blackGraph = new ArrayList();
        }

        /// <summary>
        /// method used to find out whether robot can jump or not
        /// </summary>
        /// <param name="i"></param>
        /// <param name="j"></param>
        /// <returns></returns>
        private bool Jump(int i, int j)
        {
            for (int t = 0; t < graph.GetLength(0); ++t)
            {
                if (graph[i, t] > 0 && graph[t, j] > 0)
                    return true;
            }
            return false;
        }

        /// <summary>
        /// 'Colors' peak of the graph in black if jump is not possible
        /// </summary>
        private void Black()
        {
            for (int i = 1; i < graph.GetLength(0); ++i)
                if (!whiteGraph.Contains(i))
                    blackGraph.Add(i);
        }

        /// <summary>
        /// 'Colors' peak of the graph in white if jump is possible
        /// </summary>
        /// <param name="current"></param>
        private void White(int current)
        {
            whiteGraph.Add(current);
            for (int i = 0; i < graph.GetLength(0); ++i)
                if (Jump(current, i) && !whiteGraph.Contains(i))
                    White(i);
        }

        /// <summary>
        /// Number of robots
        /// </summary>
        /// <param name="subGraph"></param>
        /// <returns></returns>
        private int Robots(ArrayList subGraph)
        {
            int number = 0;
            for (int i = 0; i < graph.GetLength(0); ++i)
                if (subGraph.Contains(i) && robots[i] > 0)
                    number++;
            return number;
        }

        /// <summary>
        /// Method used to find out if we have solutions or not
        /// </summary>
        /// <returns></returns>
        public bool Destruct()
        {
            White(0);
            Black();
            return (Robots(whiteGraph) != 1 && Robots(blackGraph) != 1);
        }

        private byte[,] graph;
        private byte[] robots;
        private ArrayList whiteGraph;
        private ArrayList blackGraph;
    }

}
