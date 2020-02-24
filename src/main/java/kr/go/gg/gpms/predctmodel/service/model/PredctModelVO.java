package kr.go.gg.gpms.predctmodel.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 예측 모델
 *
 * @Class Name : PredctModelVO.java
 * @Description : PredctModel VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2020-02-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class PredctModelVO extends BaseVO {

	public PredctModelVO() {
		super();
	}

	/**
	 * 조사_년도
	 */
	@XmlElement
	private String SLCTN_YEAR;

    /**
     * 균열_종류_구분(LC: 선형, AC: 면형, RD: 소성변형, IRI: 종단평탄성, GPCI: GPCI)
     */
    private String predctModelKndSe;

	@JsonProperty(value="SLCTN_YEAR")
	public String getSLCTN_YEAR() {
		return this.SLCTN_YEAR;
	}

	public void setSLCTN_YEAR(String slctnYear) {
		this.SLCTN_YEAR = slctnYear;
	}

    public String getPredctModelKndSe() {
        return predctModelKndSe;
    }

    public void setPredctModelKndSe(String predctModelKndSe) {
        this.predctModelKndSe = predctModelKndSe;
    }


}
