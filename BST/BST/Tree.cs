using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BST
{
    public class Tree
    {
        /// <summary>
        /// Tree constructor
        /// </summary>
        public Tree()
        {
            this.tree = null;
        }

        public Tree Left { get; set; }
        public Tree Right { get; set; }
        public int Value { get; set; }

        /// <summary>
        /// Builds iterable tree
        /// </summary>
        /// <returns></returns>
        public IEnumerable<Tree> GetAll()
        {
            if (Left != null)
            {
                foreach (Tree f in Left.GetAll())
                {
                    yield return f;
                }
            }

            yield return this;

            if (Right != null)
            {
                foreach (Tree f in Right.GetAll())
                {
                    yield return f;
                }
            }
        }

        /// <summary>
        /// Prints tree
        /// </summary>
        public void Print()
        {
            foreach (Tree t in GetAll())
            {
                Console.WriteLine(t.Value);
            }
        }

        /// <summary>
        /// Element searching
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public bool Search(int value)
        {
            if (Value == value)
                return true;
            else
            {
                if (Value > value)
                {
                    if (Right == null)
                        return false;
                    else
                    {
                        Right.length = length;
                        return Right.Search(value);
                    }
                }
                else
                {
                    if (Left == null)
                        return false;
                    else
                    {
                        Left.length = length;
                        return Left.Search(value);
                    }
                }
            }
        }

        /// <summary>
        /// Element addition
        /// </summary>
        /// <param name="value"></param>
        public void Add(int value)
        {
            if (length == 0)
                Value = value;
            else
            {
                if (Value > value)
                {
                    if (Left == null)
                    {
                        Left = new Tree();
                        Left.Value = value;
                        Left.length++;
                    }
                    else
                    {
                        Left.Add(value);
                    }
                }
                else
                {
                    if (Right == null)
                    {
                        Right = new Tree();
                        Right.Value = value;
                        Right.length++;
                    }
                    else
                    {
                        Right.Add(value);
                    }
                }
            }
            length++;
        }

        private Tree tree = null;
        private int length = 0;
    }
}
