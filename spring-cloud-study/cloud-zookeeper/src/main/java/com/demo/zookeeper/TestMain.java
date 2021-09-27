/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/1 11:17
 */
public class TestMain {


    private static String remoteAddress = "localhost:2181";

    private static CuratorFramework zkClient;

    private static String path = "/myWatch";

    private static void init() {


        zkClient =
                CuratorFrameworkFactory.builder().
                        connectString(remoteAddress)//zkClint连接地址
//                        .connectionTimeoutMs(2000)//连接超时时间
//                        .sessionTimeoutMs(10000)//会话超时时间
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                        //重试策略
//                        .namespace("myZookeeperTest")
                        //命名空间,默认节点
                        .build();

//        zkClient =
//                CuratorFrameworkFactory.newClient(remoteAddress,
//                        new ExponentialBackoffRetry(2000, 3));
        zkClient.start();
        System.out.println("zkClient: " + zkClient);
        System.out.println("==========启动zookeeper连接=========\r\n");
    }

    public static void main(String[] args) throws Exception {


        TestMain testMain = new TestMain();
        init();

        Stat stat = zkClient.checkExists().forPath(path);
        if (null == stat) {
            zkClient.create().forPath(path);

//            zkClient.delete().deletingChildrenIfNeeded().forPath(path);
        }


        childListen();
        nodeListen();
//        treeListen();

//        CHILD_ADDED

        zkClient.create().forPath("/myWatch/child22","生个好孩子".getBytes());
        Thread.sleep(2000);
        zkClient.create().creatingParentContainersIfNeeded().forPath("/myWatch/child22/child22","生个好孩子ddd".getBytes());
//        zkClient.setData().forPath("/myWatch/child333", "生个好孩子222".getBytes());

//        zkClient.create().forPath("/myWatch/child333");
        System.out.println("\r\n========删除节点========\r\n");
//        zkClient.delete().deletingChildrenIfNeeded().forPath("/myWatch/child22");
        System.in.read();   //阻塞不然启动后clint就关掉了
    }

    private static void childListen() throws Exception {
        PathChildrenCache pathChildrenCache = pathChildrenCache = new PathChildrenCache(zkClient, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {

                PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                ChildData childData = pathChildrenCacheEvent.getData();

                System.out.println("\r\n ===pathChildrenCacheListener::::->type:" + type.name() + " childData：" + childData);
//                String data = new String(childData.getData());
//                System.out.println("数据：" + data);
                System.out.println("childListen-路径：" + childData.getPath());
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);//注册监听事件
        pathChildrenCache.start();
    }

    public static void nodeListen() throws Exception {
        NodeCache nodeCache = new NodeCache(zkClient, path, false);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if (nodeCache.getCurrentData() == null) {
                    System.out.println("\r\n==节点被删除。。。" + nodeCache.getPath() + "数据为：" + new String(nodeCache.getCurrentData().getData()));
                } else {
                    System.out.println("\r\n==节点被修改。。。" + nodeCache.getPath() + "数据为：" + new String(nodeCache.getCurrentData().getData()));
                }
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    public static void treeListen() throws Exception {

        TreeCache treeCache = new TreeCache(zkClient, path);
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println("\r\n==treeCacheListener::::->" + treeCacheEvent.getData());
                System.out.println("type:"+treeCacheEvent.getType().name());
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }

    public CuratorFramework getZkClient() {
        return zkClient;
    }
}
