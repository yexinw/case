package com.gang.study.adplugin.crud;


import com.gang.study.adplugin.common.ADConfig;
import com.gang.study.adplugin.utils.ADSyncUtils;
import net.tirasa.connid.bundles.ad.crud.ADCreate;
import net.tirasa.connid.bundles.ad.crud.ADDelete;
import net.tirasa.connid.bundles.ad.crud.ADUpdate;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.Uid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import para.cic.sync.common.api.AbstractGroupOutputService;
import para.cic.sync.common.lib.to.AbstractConfigTO;
import para.cic.sync.common.lib.to.DeleteInfo;
import para.cic.sync.common.lib.to.SearchTO;

import java.util.HashSet;
import java.util.Set;

/**
 * @Classname ADCreate
 * @Description TODO
 * @Date 2019/12/2 18:04
 * @Created by zengzg
 */
@Component
public class OrgOutputImpl extends AbstractGroupOutputService<String, String> {

    @Autowired
    private ADConfig adConfig;

    private ADCreate adCreate;

    private ADUpdate adUpdate;

    private ADDelete adDelete;

    ObjectClass objectClass = new ObjectClass("__ORGANIZATION__");

    @Override
    public void init(AbstractConfigTO abstractConfigTO) {
        test();
        adCreate = new ADCreate(adConfig.getAdConnection(), objectClass, getInfo(), null);
    }

    @Override
    public String checkAccessToken() {
        return null;
    }

    @Override
    public String create(String s, AbstractConfigTO abstractConfigTO) {
        Uid ui = adCreate.create();
        return ui.getUidValue();
    }

    @Override
    public String update(String s, AbstractConfigTO abstractConfigTO) {
        adUpdate = new ADUpdate(adConfig.getAdConnection(), objectClass, new Uid("535de16d-3bdf-4e8e-a394-849915a0bf06"));
        return adUpdate.update(getInfo()).getUidValue();
    }

    /*
     271
     */
    @Override
    public String delete(DeleteInfo deleteInfo, AbstractConfigTO abstractConfigTO) {
        adDelete = new ADDelete(adConfig.getAdConnection(), objectClass, new Uid("751af60a-878f-4ba4-a8e9-33839d0cf6f7"));
        adDelete.delete();
        return "delete ok";
    }

    @Override
    public AbstractConfigTO checkConfig() {
        return null;
    }

    @Override
    public String get(SearchTO searchTO, AbstractConfigTO abstractConfigTO) {
        return null;
    }

    @Override
    public String list(SearchTO searchTO, AbstractConfigTO abstractConfigTO) {
        return null;
    }

    @Override
    public String listChild(SearchTO searchTO, AbstractConfigTO abstractConfigTO) {
        return null;
    }

    public void test() {
        adConfig.getAdConnection().test();
    }

    public Set<Attribute> getInfo() {
        Set<Attribute> attrs = new HashSet<Attribute>();


        // V 2.0 其他数据准备
        /*
        上海派拉  : 5a58a54e-caed-462e-b80e-23e57abf2dd5
        武汉研发 : 4ff83fee-d6e0-4b44-bd23-cc2a27233a32
         */
        String nameString = ADSyncUtils.dataCreateUtil("name", "武汉研发");
        attrs.add(AttributeBuilder.build("__NAME__", nameString));
        attrs.add(AttributeBuilder.build("__ORG__", "5a58a54e-caed-462e-b80e-23e57abf2dd5"));
        return attrs;
    }
}
