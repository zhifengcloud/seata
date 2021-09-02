/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.test.zookeeper;

import com.demo.zookeeper.ZookeeperApplication;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/1 10:26
 */
@SpringBootTest(classes = ZookeeperApplication.class)
@RunWith(SpringRunner.class)
public class ZookeeperTest {

    private String remoteAddress = "localhost:2181";

    private CuratorFramework zkClient;

    private String path = "/test_yzf";

    @Before
    public void zkClient() {
        zkClient =
                CuratorFrameworkFactory.newClient(remoteAddress,
                        new ExponentialBackoffRetry(2000, 3));
        zkClient.start();
        System.out.println("zkClient: " + zkClient);
        System.out.println("==========启动zookeeper连接=========\r\n");
    }

    @Test
    public void createPathTest() {

        Stat stat = null;
        try {
            stat = zkClient.checkExists().forPath(path);
            if (stat == null) {
                zkClient.create().creatingParentContainersIfNeeded()
                        //节点类型（EPHEMERAL_SEQUENTIAL：临时序列节点；PERSISTENT_SEQUENTIAL：持久序列节点）
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE) //节点权限
                        .forPath(path, "123".getBytes());
            } else {
                zkClient.setData().forPath(path, "456".getBytes());
            }
            String data = new String(zkClient.getData().storingStatIn(new Stat()).forPath(path));
            System.out.println("节点数据：" + data);


            listenTest();
            zkClient.setData().forPath(path, "666".getBytes());

            System.out.println("节点数据：" + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDateTest() throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        Assert.assertNotNull(stat);
        if (stat != null) {
            System.out.println("length:" + stat.getDataLength());
            String data = new String(zkClient.getData().storingStatIn(stat).forPath(path));
            System.out.println("stat:" + stat);
            System.out.println("data:" + data);
        }

    }


    public void listenTest() {
//        NodeCache，一次性给节点注册多个监听，可以监听到当前节点的增删改
        NodeCache nodeCache = new NodeCache(zkClient, path);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if (nodeCache.getCurrentData() == null) {
                    System.out.println("节点被删除。。。" + nodeCache.getPath() + "数据为：" + new String(nodeCache.getCurrentData().getData()));
                } else {
                    System.out.println("节点被修改。。。" + nodeCache.getPath() + "数据为：" + new String(nodeCache.getCurrentData().getData()));
                }
            }
        });


        //11,PathChildrenCache,监听父节点下的所有子节点（可监听多次）
//        PathChildrenCache pathChildrenCache = new PathChildrenCache(zkClient,"/",true);
//        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
//            @Override
//            public void childEvent(CuratorFramework client2, PathChildrenCacheEvent event) throws Exception {
//                if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
//                    //CHILD_ADDED:类型为增加；INITIALIZED:类型为初始化；CHILD_REMOVED:类型为删除；CHILD_UPDATED：类型为修改
//                    System.out.println("节点增加了。。。路径为："+event.getData().getPath()+"数据为"+new String(event.getData().getData()));
//                }
//            }
//        });
    }


    @Test
    public void deletePathTest() throws Exception {
        zkClient.delete()
                .guaranteed()//保障机制，若未删除成功，会话有效期内一直尝试删除
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .forPath(path);
    }

    @After
    public void destory() {
        //  关闭zookeeper连接
        System.out.println("==========关闭zookeeper连接=========\r\n");
        CloseableUtils.closeQuietly(zkClient);
    }
}
