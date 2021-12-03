package com.mit.pawin.tree;

public class SampleData {

	public static TreeNode<String> getSet1() {
		TreeNode<String> root = new TreeNode<String>("Admin");
		{
			TreeNode<String> node0 = root.addChild("Manager");
			//TreeNode<String> node1 = root.addChild("node1");
			//TreeNode<String> node2 = root.addChild("node2");
			{
				TreeNode<String> node20 = node0.addChild("Supervisor");
				//TreeNode<String> node21 = node2.addChild("node21");
				{
					TreeNode<String> node210 = node20.addChild("Test 1");
					TreeNode<String> node211 = node20.addChild("Test 2");
				}
			}
			TreeNode<String> node3 = root.addChild("Service Manager");
//			{
//				TreeNode<String> node30 = node3.addChild("node30");
//			}
		}

		/*TreeNode<String> root = new TreeNode<String>("Admin");
		root.addChild("Manager");
		root.addChild("Service Manager");
		root.addChild("Manager").addChild("Supervisor");
		root.addChild("Manager").addChild("Supervisor").addChild("Test 1");
		root.addChild("Manager").addChild("Supervisor").addChild("Test 2");*/

		return root;
	}

	public static TreeNode<String> getSetSOF() {
		TreeNode<String> root = new TreeNode<String>("root");
		{
			TreeNode<String> node0 = root.addChild("node0");
			TreeNode<String> node1 = root.addChild("node1");
			TreeNode<String> node2 = root.addChild("node2");
			{
				TreeNode<String> node20 = node2.addChild(null);
				TreeNode<String> node21 = node2.addChild("node21");
				{
					TreeNode<String> node210 = node20.addChild("node210");
				}
			}
		}

		return root;
	}
}