package com.ppj.algorithm.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 有 n 个人，每个人都有一个 0 到 n-1 的唯一 id 。
 *
 * <p>给你数组 watchedVideos 和 friends ，其中 watchedVideos[i] 和 friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
 *
 * <p>Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k
 * 的好友观看过的视频。
 *
 * <p>给定你的 id 和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。
 *
 * <p>示例 1：
 *
 * <p>输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id
 * = 0, level = 1 输出：["B","C"] 解释： 你的 id 为 0（绿色），你的朋友包括（黄色）： id 为 1 -> watchedVideos = ["C"] id 为 2
 * -> watchedVideos = ["B","C"] 你朋友观看过视频的频率为： B -> 1 C -> 2 示例 2：
 *
 * <p>输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id
 * = 0, level = 2 输出：["D"] 解释： 你的 id 为 0（绿色），你朋友的朋友只有一个人，他的 id 为 3（黄色）。
 *
 * @author pipi
 * @since 2021/5/12 16:33
 */
public class No1311_获取你好友已观看的视频 {

  public static void main(String[] args) {
    //    List<List<String>> videos = new ArrayList<List<String>>(){
    //      {add(new ArrayList<String>(){{add("A");add("B");}});};
    //      {add(new ArrayList<String>(){{add("C");}});};
    //      {add(new ArrayList<String>(){{add("B");add("C");}});};
    //      {add(new ArrayList<String>(){{add("D");}});};
    //    };
    //    int[][] friends = new int[][]{{1,2},{0,3},{0,3},{1,2}};
    //    int id = 0;
    //    int level = 2;
    //    System.out.println(new
    // No1311_获取你好友已观看的视频().watchedVideosByFriends(videos,friends,id,level));
  }

  public List<String> watchedVideosByFriends(
      List<List<String>> watchedVideos, int[][] friends, int id, int level) {
    List<Integer> friendIdList = getFriends(friends, level, id);
    List<String> video = new ArrayList<>();
    for (int friendId : friendIdList) {
      final List<String> strings = watchedVideos.get(friendId);
      video.addAll(strings);
    }
    List<String> res = new ArrayList<>();
    video.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .sorted(Map.Entry.comparingByValue())
        .forEachOrdered(e -> res.add(e.getKey()));
    return res;
  }

  private List<Integer> getFriends(int[][] friends, int level, int id) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    queue.offer(id);
    visited.add(id);
    int len = 0;
    while (!queue.isEmpty() && len < level) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Integer idd = queue.poll();
        for (int j = 0; j < friends[idd].length; j++) {
          if (!visited.contains(friends[idd][j])) {
            queue.add(friends[idd][j]);
            visited.add(friends[idd][j]);
          }
        }
      }
      len++;
    }
    return new ArrayList<>(queue);
  }
  //
  //  private List<Integer> getFriends(int[][] friends, int level, int id) {
  //    Node myNode = new Node(id);
  //    for (int[] friend : friends) {
  //      for (int i = 0; i < friend.length; i++) {
  //        if(myNode.contains(friend[i])){
  //          continue;
  //        }
  //        Node node = myNode.getNodeByFriendId(i);
  //
  //
  //        myNode.add(new Node(friend[i]));
  //      }
  //    }
  //  }
  //
  //  static class Node {
  //    int id;
  //    List<Node> friendList = new ArrayList<>();
  //
  //    public Node(int id) {
  //      this.id = id;
  //    }
  //
  //    public boolean contains(int friend) {
  //      final boolean oo = this.id == friend;
  //      if (oo) {
  //        return true;
  //      }
  //      for (Node node : friendList) {
  //        if (node.contains(friend)) {
  //          return true;
  //        }
  //      }
  //      return false;
  //    }
  //
  //    public void add(Node node) {
  //      friendList.add(node);
  //    }
  //  }
}
