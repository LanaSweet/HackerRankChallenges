    private static boolean checkForCircle(Map<Integer, List<Integer>> mapNodes, int ver1, int ver2, int n) {
		if(mapNodes.get(ver1)==null || mapNodes.get(ver2)==null) {
			return false;
		}
		Queue<Integer> toVisit = new LinkedList<>();
		boolean[] visited = new boolean[n];
		for(Integer node: mapNodes.get(ver1)) {
			if(node!=ver2) {
				toVisit.add(node);
				visited[node] = true;
			}
		}
		while(!toVisit.isEmpty()) {
			Integer cur = toVisit.poll();
			if(mapNodes.get(cur)!=null && !mapNodes.get(cur).isEmpty()) {
				for(Integer node: mapNodes.get(cur)) {
					if (node==ver2) {
						return true;
					}
					if(!visited[node] ) {
						toVisit.add(node);
					}
				}
				
			}
			visited[cur]=true;
			
		}
		
		return false;
	}
