package gmall.model;

import java.sql.*;
import java.util.ArrayList;

/**
 * 处理,和Goods表相关的业务逻辑
 * <p>
 * Created by elephant on 16/7/8.
 */
public class GoodsBeanBO {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 通过 ID 获取 货物的详细信息（GoodsBean对象）
	 *
	 * @param id
	 * @return
	 */
	public GoodsBean getGoodsBean(String id) {
		GoodsBean gb = new GoodsBean();
		try {
			DBConnection connDB = new DBConnection();
			con = connDB.getConnection();
			ps = con.prepareStatement("SELECT * FROM Item WHERE id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
                                gb.setGoodsId(rs.getInt(1));
                                gb.setGoodsName(rs.getString(2));
                                gb.setGoodsPrice(rs.getFloat(3));
                                gb.setGoodsBrandId(rs.getInt(4));
                                gb.setGoodsStar(rs.getFloat(5));
                                gb.setGoodsUsercount(rs.getInt(6));
                                gb.setGoodsScore(rs.getFloat(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return gb;
	}

	/**
	 * 分页
	 *
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public ArrayList getGoodsByPage(int pageSize, int pageNow) {
		ArrayList al = new ArrayList();
		try {
			DBConnection connDB = new DBConnection();
			con = connDB.getConnection();
			ps = con.prepareStatement("SELECT * FROM Item LIMIT ?,?");
				ps.setInt(1,(pageNow-1)*pageSize);
				ps.setInt(2,pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsBean gb = new GoodsBean();
                                gb.setGoodsId(rs.getInt(1));
                                gb.setGoodsName(rs.getString(2));
                                gb.setGoodsPrice(rs.getFloat(3));
                                gb.setGoodsBrandId(rs.getInt(4));
                                gb.setGoodsStar(rs.getFloat(5));
                                gb.setGoodsUsercount(rs.getInt(6));
                                gb.setGoodsScore(rs.getFloat(7));
				al.add(gb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return al;
	}

	/**
	 * 获得分页页数
	 *
	 * @param pageSize
	 * @return
	 */
	public int getPageCount(int pageSize) {
		int pageCount = 0, rowCount = 0;
		try {
			DBConnection connDB = new DBConnection();
			con = connDB.getConnection();
			ps = con.prepareStatement("SELECT count(*) FROM Item");
			rs = ps.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}
		} catch (Exception e) {

		} finally {
			this.close();
		}
		return pageCount;
	}

	/**
	 * 用来关闭数据库链接资源
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
