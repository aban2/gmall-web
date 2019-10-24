package gmall.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Collections;



public class GoodsHandle {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    public GoodsHandle() {
        try {
            this.conn = new DBConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // target: star, price score(default)
    // order: ASC, DESC
    public List<GoodsBean> searchItem(String target, String keyword, String order) throws Exception {
        List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
        String sql = "SELECT * FROM Item WHERE name LIKE ? ORDER BY ? ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%"+keyword+"%");
        this.pstmt.setString(2, target);
        this.pstmt.setString(3, order);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            GoodsBean good = new GoodsBean();
            good.setGoodsId(rs.getInt(1));
            good.setGoodsName(rs.getString(2));
	    good.setGoodsPrice(rs.getFloat(3));
	    good.setGoodsBrandId(rs.getInt(4));
            good.setGoodsStar(rs.getFloat(5));
            good.setGoodsUsercount(rs.getInt(6));
            good.setGoodsScore(rs.getFloat(7));
            goodsList.add(good);
        }
        rs.close();
        this.pstmt.close();
        return goodsList;
    }


    public int getMaxId() throws Exception {
        ResultSet rs = this.conn.prepareStatement("select max(id) from goods").executeQuery();
        rs.next();
        int maxId = rs.getInt(1);
        return maxId;
    }
    
    public List<GoodsBean> findByBrand(String brandName) throws Exception {
        List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
        GoodsBean goods = null;
        String sql = "SELECT id,name,price,brandId,star,usercount,score FROM Item INNER JOIN Brand"
                + "ON Item.brandId=Brand.id WHERE Brand.name=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, brandName);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            GoodsBean good = new GoodsBean();
            good.setGoodsId(rs.getInt(1));
            good.setGoodsName(rs.getString(2));
	    good.setGoodsPrice(rs.getFloat(3));
	    good.setGoodsBrandId(rs.getInt(4));
            good.setGoodsStar(rs.getFloat(5));
            good.setGoodsUsercount(rs.getInt(6));
            good.setGoodsScore(rs.getFloat(7));
            goodsList.add(good);
        }
        rs.close();
        this.pstmt.close();
        return goodsList;
    }
    
    public List<GoodsBean> findByColour(String colour) throws Exception {
        List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
        GoodsBean goods = null;
        String sql = "SELECT id,name,price,brandId,star,usercount,score FROM Item INNER JOIN Colour"
                + "ON Item.id=Colour.itemId WHERE Colour.name=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, colour);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            GoodsBean good = new GoodsBean();
            good.setGoodsId(rs.getInt(1));
            good.setGoodsName(rs.getString(2));
	    good.setGoodsPrice(rs.getFloat(3));
	    good.setGoodsBrandId(rs.getInt(4));
            good.setGoodsStar(rs.getFloat(5));
            good.setGoodsUsercount(rs.getInt(6));
            good.setGoodsScore(rs.getFloat(7));
            goodsList.add(good);
        }
        rs.close();
        this.pstmt.close();
        return goodsList;
    }
    
    public List<GoodsBean> findByBrandAndColour(String brand, String colour) throws Exception {
        List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
        String sql = "SELECT id,name,price,brandId,star,usercount,score FROM (SELECT * FROM Item INNER JOIN Brand ON Item.brandId=Brand.id WHERE Brand.name=?) INNER JOIN Color ON Item.id=Colour.ItenId WHERE Colour.name=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, brand);
        this.pstmt.setString(2, colour);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            GoodsBean good = new GoodsBean();
            good.setGoodsId(rs.getInt(1));
            good.setGoodsName(rs.getString(2));
	    good.setGoodsPrice(rs.getFloat(3));
	    good.setGoodsBrandId(rs.getInt(4));
            good.setGoodsStar(rs.getFloat(5));
            good.setGoodsUsercount(rs.getInt(6));
            good.setGoodsScore(rs.getFloat(7));
            goodsList.add(good);
        }
        rs.close();
        this.pstmt.close();
        return goodsList;
    }

    public void userClickItem_addInfo(String userName) throws Exception {
        String sql = "UPDATE Userinfo SET tagb = 1 WHERE userId=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, userName);
        this.pstmt.executeQuery();
        this.pstmt.close();
    }
    
    public void userClickItem_changeWeight(int itemId, String userId) throws Exception{
        List<String> tags = new ArrayList<String>();
        String sql = "SELECT name FROM Tag WHERE itemId=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, itemId);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
            tags.add(rs.getString(1));
        }
        for(int i = 0; i < tags.size(); i++){
            sql = "SELECT * FROM Usertag WHERE userId=? and tag=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, userId);
            this.pstmt.setString(2, tags.get(i));
            rs = this.pstmt.executeQuery();
            if(rs.next()){
                sql = "UPDATE Usertag SET weight = weight +3 WHERE userId = ? AND tag = ?";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, userId);
                this.pstmt.setString(2, tags.get(i));
                this.pstmt.executeQuery();
            }
            else{
                sql = "INSERT INTO Usertag VALUES (?, ?, '3')";
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, userId);
                this.pstmt.setString(2, tags.get(i));
                this.pstmt.executeQuery();
            }
            rs.close();
            this.pstmt.close();
        }
    }
    
    
    public GoodsDetail getItemDetail(int itemId) throws Exception{
        GoodsDetail gd = new GoodsDetail();
        gd.setGoodsId(itemId);
        
        String sql = "SELECT Item.name, price, Brand.name FROM Item INNER JOIN Brand On Item.id=Brand.itemId WHERE itemId=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, itemId);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            gd.setGoodsName(rs.getString(1));
            gd.setGoodsPrice(rs.getFloat(2));
            gd.setGoodsBrandId(rs.getString(3));
        }
        sql = "SELECT rdate, star, comment FROM Review WHERE itemID=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, itemId);
        if(rs.next()){
            gd.setGoodsRdate(rs.getString(1));
            gd.setGoodsStar(rs.getFloat(2));
            gd.setGoodsComment(rs.getString(3));
        }
        rs.close();
        this.pstmt.close();
        return gd;
    }
    
    public List<GoodsBean> recommand_item(int itemId) throws Exception{
        HashMap<String, Integer> weight = new HashMap<String, Integer>();
        String sql = "SELECT name FROM Tag WHERE itemId=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, itemId);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
            sql = "SELECT itemId FROM Tag WHERE name=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, rs.getString(1));
            ResultSet rs2 = this.pstmt.executeQuery();
            while(rs2.next())
            {
                if(weight.get(rs2.getInt(1)) != null){
                    int temp = weight.get(rs2.getInt(1));
                    weight.put(rs2.getInt(1)+"", temp++);
                }
                else{
                    weight.put(rs2.getInt(1)+"", 1);
                }
            }
        }
        weight = sortHashMap(weight);
        List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
        int count = 0;
        for(String a: weight.keySet()){
            sql = "SELECT id,name,price,brandId,star,usercount,score FROM Item WHERE id =?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setInt(1, Integer.parseInt(a));
            ResultSet rs3 = this.pstmt.executeQuery();
            while (rs3.next()) {
                GoodsBean good = new GoodsBean();
                good.setGoodsId(rs3.getInt(1));
                good.setGoodsName(rs3.getString(2));
                good.setGoodsPrice(rs3.getFloat(3));
                good.setGoodsBrandId(rs3.getInt(4));
                good.setGoodsStar(rs3.getFloat(5));
                good.setGoodsUsercount(rs3.getInt(6));
                good.setGoodsScore(rs3.getFloat(7));
                goodsList.add(good);
            }
            count++;
            rs.close();
            this.pstmt.close();
            if(count == 3)
                break;
        }
        return goodsList;
    }
    
    public List<GoodsBean> recommand_user(int userId) throws Exception{
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String sql = "SELECT tag, weight FROM Usertag WHERE userId=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, userId);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
            int weight = rs.getInt(2);
            sql = "SELECT itemId FROM Tag WHERE name=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, rs.getString(1));
            ResultSet rs2 = this.pstmt.executeQuery();
            while(rs2.next())
            {
                if(map.get(rs2.getInt(1)) != null){
                    int temp = map.get(rs2.getInt(1));
                    map.put(rs2.getInt(1)+"", temp+weight);
                }
                else{
                    map.put(rs2.getInt(1)+"", weight);
                }
            }
        }
        map = sortHashMap(map);
        List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
        int count = 0;
        for(String a: map.keySet()){
            sql = "SELECT id,name,price,brandId,star,usercount,score FROM Item WHERE id =?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setInt(1, Integer.parseInt(a));
            ResultSet rs3 = this.pstmt.executeQuery();
            while (rs3.next()) {
                GoodsBean good = new GoodsBean();
                good.setGoodsId(rs3.getInt(1));
                good.setGoodsName(rs3.getString(2));
                good.setGoodsPrice(rs3.getFloat(3));
                good.setGoodsBrandId(rs3.getInt(4));
                good.setGoodsStar(rs3.getFloat(5));
                good.setGoodsUsercount(rs3.getInt(6));
                good.setGoodsScore(rs3.getFloat(7));
                goodsList.add(good);
            }
            count++;
            rs.close();
            this.pstmt.close();
            if(count == 8)
                break;
        }
        return goodsList;
    }
    
    public HashMap<String,Integer> sortHashMap(HashMap<String,Integer> map){
        HashMap<String,Integer> sortedMap = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Iterator<String> item = map.keySet().iterator();
        while(item.hasNext()){
            list.add(item.next());
        }
        Collections.sort(list);
        Iterator<String> item2 = list.iterator();
        while(item2.hasNext()){
            String key = item2.next();
            sortedMap.put(key,(map.get(key)));
        }
        return sortedMap;
}
    
	public void close(){
		if(this.conn != null){
			try{
				this.conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
