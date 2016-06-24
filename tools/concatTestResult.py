#!/usr/bin/python  
# -*- coding=utf-8 -*-  

import os 
import os.path 
import shutil 
import time,datetime
import argparse
from xml.etree.ElementTree import ElementTree,Element  
  
def read_xml(in_path):  
    '''''读取并解析xml文件 
       in_path: xml路径 
       return: ElementTree'''  
    tree = ElementTree()  
    tree.parse(in_path)  
    return tree  
  
def write_xml(tree, out_path):  
    '''''将xml文件写出 
       tree: xml树 
       out_path: 写出路径'''  
    tree.write(out_path, encoding="utf-8",xml_declaration=True)  
  
def find_nodes(tree, path):  
    '''''查找某个路径匹配的所有节点 
       tree: xml树 
       path: 节点路径'''  
    return tree.findall(path)  
  
 

if __name__ == "__main__":  
    import argparse
    parser = argparse.ArgumentParser(description='process images....')
    parser.add_argument("-goal", type=str, help="goal xml path")
    parser.add_argument("-temp", type=str, help="temp xml path")  
    args = parser.parse_args()

    #1. 读取xml文件
    if os.path.exists(args.goal)==True:
      goal_tree = read_xml(args.goal)
      goal_root = goal_tree.getroot()
      
      temp_tree = read_xml(args.temp)
      temp_nodes = find_nodes(temp_tree, "testcase")
      for node in temp_nodes:
        goal_root.append(node)
      #6. 输出到结果文件 
      write_xml(goal_tree, args.goal) 
    else:
      #直接拷贝src2到src1
      shutil.copy(args.temp,args.goal)
    