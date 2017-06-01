package com.sanker.action.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sanker.action.DefaultAction;
import com.sanker.entity.game.Ledger;
import com.sanker.service.game.LedgerService;
import com.sanker.service.utils.JSONHelper;

/**
 * 流水
 * @author 滕洁
 * @date 2016-11-26
 */
public class LedgerAction extends DefaultAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LedgerService ledgerService;

	public LedgerService getLedgerService() {
		return ledgerService;
	}

	public void setLedgerService(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
	}

	/**
	 * 新增流水
	 */
	public void addLedger() {
		Ledger entity = new Ledger();
		entity.setPlayerId(getString("playerId"));
		entity.setLedgerType(getString("ledgerType"));
		entity.setLedgerNum(getInteger("ledgerNum"));
		entity.setLedgerScore(getInteger("ledgerScore"));
		entity.setTarget(getString("target"));
		this.ledgerService.addLedger(entity);
	}

	/**
	 * 查询流水
	 * 需要判断是否当前对局的类型
	 */
	public void getLedgerList() {
		List<Ledger> ledgerList = new ArrayList<Ledger>();
		boolean allFlag = false;
		if (getInteger("gameNum") == null || getInteger("gameNum") == 0) {
			ledgerList = this.ledgerService.getListByPlayer(getString("playerId"));
			if (getString("type") != null && getString("type").equals("friend")) {
				allFlag = true;
			}
		} else {
			ledgerList = this.ledgerService.getListByPlayer(getString("playerId"), getInteger("gameNum"));
		}

		Integer sum = 0;
		Map<String, Integer> kindMsg = new HashMap<String, Integer>();
		for (Ledger ledger : ledgerList) {
			sum += ledger.getLedgerScore();
			if (kindMsg.containsKey(ledger.getLedgerName())) {
				kindMsg.put(ledger.getLedgerName(), kindMsg.get(ledger.getLedgerName()) + 1);
			} else {
				kindMsg.put(ledger.getLedgerName(), 1);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", "getLedgerList");
		map.put("position", getString("position"));
		map.put("sum", sum);
		if (allFlag) {
			map.put("values", kindMsg);
		} else {
			map.put("values", ledgerList);
		}

		String json = JSONHelper.toJson(map);
		System.out.println(json + "获取流水！");
		Write(json);
	}

	/**
	 * 查询流水详情
	 * 需要判断是否当前对局的类型
	 */
	public void getLedgerDetailList() {
		List<Ledger> ledgerList = new ArrayList<Ledger>();
		if (getInteger("gameNum") == null || getInteger("gameNum") == 0) {
			ledgerList = this.ledgerService.getListByPlayer(getString("playerId"));
		} else {
			ledgerList = this.ledgerService.getListByPlayer(getString("playerId"), getInteger("gameNum"));
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", "getLedgerList");
		map.put("position", getString("position"));
		map.put("values", ledgerList);

		String json = JSONHelper.toJson(map);
		Write(json);
	}

	/**
	 * 删除流水记录
	 */
	public void deleteLedger() {

		this.ledgerService.deleteLedger(getString("playerId"));
	}

	/**
	 * 根据玩家ID查询流水总计
	 */
	public void getSumByPlayer() {
		System.out.println(getString("position"));
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("position", getString("position"));
		mapObj.put("ledgerSum", this.ledgerService.getLedgerSumByPlayer(getString("playerId")));
		//好友场根据玩家Id和对局编号查询
		if (getInteger("gameNum") != null && getInteger("gameNum") > 0)
			mapObj.put("oneLedgerSum", this.ledgerService.getLedgerSumByPlayer(getString("playerId"), getInteger("gameNum")));
		String mapStr = JSONHelper.toJson(mapObj);
		Write(mapStr);
	}

}
