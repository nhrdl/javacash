//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl;

public class GncTemplateTransactionsTypeImpl implements biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTemplateTransactionsType, com.sun.xml.bind.JAXBObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallableObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializable, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.ValidatableObject
{

    protected com.sun.xml.bind.util.ListImpl _GncTransaction;
    protected com.sun.xml.bind.util.ListImpl _GncAccount;
    public final static java.lang.Class version = (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTemplateTransactionsType.class);
    }

    protected com.sun.xml.bind.util.ListImpl _getGncTransaction() {
        if (_GncTransaction == null) {
            _GncTransaction = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _GncTransaction;
    }

    public java.util.List getGncTransaction() {
        return _getGncTransaction();
    }

    protected com.sun.xml.bind.util.ListImpl _getGncAccount() {
        if (_GncAccount == null) {
            _GncAccount = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _GncAccount;
    }

    public java.util.List getGncAccount() {
        return _getGncAccount();
    }

    public biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
        return new biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTemplateTransactionsTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_GncTransaction == null)? 0 :_GncTransaction.size());
        int idx2 = 0;
        final int len2 = ((_GncAccount == null)? 0 :_GncAccount.size());
        while (idx2 != len2) {
            if (_GncAccount.get(idx2) instanceof javax.xml.bind.Element) {
                context.childAsBody(((com.sun.xml.bind.JAXBObject) _GncAccount.get(idx2 ++)), "GncAccount");
            } else {
                context.startElement("", "gnc_account");
                int idx_0 = idx2;
                context.childAsURIs(((com.sun.xml.bind.JAXBObject) _GncAccount.get(idx_0 ++)), "GncAccount");
                context.endNamespaceDecls();
                int idx_1 = idx2;
                context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _GncAccount.get(idx_1 ++)), "GncAccount");
                context.endAttributes();
                context.childAsBody(((com.sun.xml.bind.JAXBObject) _GncAccount.get(idx2 ++)), "GncAccount");
                context.endElement();
            }
        }
        while (idx1 != len1) {
            if (_GncTransaction.get(idx1) instanceof javax.xml.bind.Element) {
                context.childAsBody(((com.sun.xml.bind.JAXBObject) _GncTransaction.get(idx1 ++)), "GncTransaction");
            } else {
                context.startElement("", "gnc_transaction");
                int idx_2 = idx1;
                context.childAsURIs(((com.sun.xml.bind.JAXBObject) _GncTransaction.get(idx_2 ++)), "GncTransaction");
                context.endNamespaceDecls();
                int idx_3 = idx1;
                context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _GncTransaction.get(idx_3 ++)), "GncTransaction");
                context.endAttributes();
                context.childAsBody(((com.sun.xml.bind.JAXBObject) _GncTransaction.get(idx1 ++)), "GncTransaction");
                context.endElement();
            }
        }
    }

    public void serializeAttributes(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_GncTransaction == null)? 0 :_GncTransaction.size());
        int idx2 = 0;
        final int len2 = ((_GncAccount == null)? 0 :_GncAccount.size());
        while (idx2 != len2) {
            if (_GncAccount.get(idx2) instanceof javax.xml.bind.Element) {
                context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _GncAccount.get(idx2 ++)), "GncAccount");
            } else {
                idx2 += 1;
            }
        }
        while (idx1 != len1) {
            if (_GncTransaction.get(idx1) instanceof javax.xml.bind.Element) {
                context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _GncTransaction.get(idx1 ++)), "GncTransaction");
            } else {
                idx1 += 1;
            }
        }
    }

    public void serializeURIs(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx1 = 0;
        final int len1 = ((_GncTransaction == null)? 0 :_GncTransaction.size());
        int idx2 = 0;
        final int len2 = ((_GncAccount == null)? 0 :_GncAccount.size());
        while (idx2 != len2) {
            if (_GncAccount.get(idx2) instanceof javax.xml.bind.Element) {
                context.childAsURIs(((com.sun.xml.bind.JAXBObject) _GncAccount.get(idx2 ++)), "GncAccount");
            } else {
                idx2 += 1;
            }
        }
        while (idx1 != len1) {
            if (_GncTransaction.get(idx1) instanceof javax.xml.bind.Element) {
                context.childAsURIs(((com.sun.xml.bind.JAXBObject) _GncTransaction.get(idx1 ++)), "GncTransaction");
            } else {
                idx1 += 1;
            }
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTemplateTransactionsType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"r\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003s"
+"r\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\u0006q\u0000~\u0000\fpsr\u0000\'c"
+"om.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClas"
+"st\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.E"
+"lementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentM"
+"odelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\fp\u0000sq\u0000~\u0000\u0006ppsq\u0000~\u0000\bq\u0000~\u0000\fpsr\u0000 com.sun.msv.gr"
+"ammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000fxq\u0000"
+"~\u0000\u0003q\u0000~\u0000\fpsr\u00002com.sun.msv.grammar.Expression$AnyStringExpress"
+"ion\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u000b\u0001psr\u0000 com.sun.msv.grammar.AnyNameC"
+"lass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"psr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0018psr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001fx"
+"q\u0000~\u0000\u001at\u0000?biz.wolschon.fileformats.gnucash.jwsdpimpl.generated"
+".GncAccountt\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000"
+"~\u0000\u000eq\u0000~\u0000\fp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000epp\u0000sq\u0000~\u0000\u0006ppsq\u0000~\u0000\bq\u0000~\u0000\fpsq\u0000~\u0000\u0014q\u0000~\u0000\fpq"
+"\u0000~\u0000\u0017q\u0000~\u0000\u001bq\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000Cbiz.wolschon.fileformats.gnucash.jwsd"
+"pimpl.generated.GncAccountTypeq\u0000~\u0000\"sq\u0000~\u0000\u0006ppsq\u0000~\u0000\u0014q\u0000~\u0000\fpsr\u0000\u001bc"
+"om.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/da"
+"tatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/St"
+"ringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'c"
+"om.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespac"
+"eUriq\u0000~\u0000\u001fL\u0000\btypeNameq\u0000~\u0000\u001fL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/dataty"
+"pe/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSc"
+"hemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor"
+"$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceP"
+"rocessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$Null"
+"SetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util.StringP"
+"air\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001fL\u0000\fnamespaceURIq\u0000~\u0000\u001fxpq\u0000~\u00008q\u0000"
+"~\u00007sq\u0000~\u0000\u001et\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-instance"
+"q\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000\u000bgnc_accountt\u0000\u0000q\u0000~\u0000\u001dsq\u0000~\u0000\u0006ppsq\u0000~\u0000\bq\u0000~\u0000\fpsq\u0000~\u0000\u0006q"
+"\u0000~\u0000\fpsq\u0000~\u0000\u000eq\u0000~\u0000\fp\u0000sq\u0000~\u0000\u0006ppsq\u0000~\u0000\bq\u0000~\u0000\fpsq\u0000~\u0000\u0014q\u0000~\u0000\fpq\u0000~\u0000\u0017q\u0000~\u0000\u001b"
+"q\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000Cbiz.wolschon.fileformats.gnucash.jwsdpimpl.gen"
+"erated.GncTransactionq\u0000~\u0000\"sq\u0000~\u0000\u000eq\u0000~\u0000\fp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000epp\u0000sq\u0000~"
+"\u0000\u0006ppsq\u0000~\u0000\bq\u0000~\u0000\fpsq\u0000~\u0000\u0014q\u0000~\u0000\fpq\u0000~\u0000\u0017q\u0000~\u0000\u001bq\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000Gbiz.wols"
+"chon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionT"
+"ypeq\u0000~\u0000\"sq\u0000~\u0000\u0006ppsq\u0000~\u0000\u0014q\u0000~\u0000\fpq\u0000~\u00000q\u0000~\u0000@q\u0000~\u0000\u001dsq\u0000~\u0000\u001et\u0000\u000fgnc_tran"
+"sactionq\u0000~\u0000Eq\u0000~\u0000\u001dsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Clos"
+"edHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j"
+"\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/gr"
+"ammar/ExpressionPool;xp\u0000\u0000\u0000\u0013\u0001pq\u0000~\u0000\u0013q\u0000~\u0000\'q\u0000~\u0000Kq\u0000~\u0000Sq\u0000~\u0000\rq\u0000~\u0000Hq"
+"\u0000~\u0000$q\u0000~\u0000Pq\u0000~\u0000\u0012q\u0000~\u0000&q\u0000~\u0000Jq\u0000~\u0000Rq\u0000~\u0000\nq\u0000~\u0000Gq\u0000~\u0000\u0007q\u0000~\u0000Fq\u0000~\u0000\u0005q\u0000~\u0000+q"
+"\u0000~\u0000Wx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
            super(context, "-------");
        }

        protected Unmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTemplateTransactionsTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  5 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  1 :
                        if (("gnc_account" == ___local)&&("" == ___uri)) {
                            _getGncAccount().add(((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncAccountImpl) spawnChildFromEnterElement((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncAccountImpl.class), 1, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("gnc_account" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 5;
                            return ;
                        }
                        if (("gnc_transaction" == ___local)&&("" == ___uri)) {
                            _getGncTransaction().add(((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTransactionImpl) spawnChildFromEnterElement((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTransactionImpl.class), 4, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("gnc_transaction" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 2;
                            return ;
                        }
                        state = 4;
                        continue outer;
                    case  0 :
                        if (("gnc_account" == ___local)&&("" == ___uri)) {
                            _getGncAccount().add(((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncAccountImpl) spawnChildFromEnterElement((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncAccountImpl.class), 1, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("gnc_account" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 5;
                            return ;
                        }
                        state = 1;
                        continue outer;
                    case  4 :
                        if (("gnc_transaction" == ___local)&&("" == ___uri)) {
                            _getGncTransaction().add(((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTransactionImpl) spawnChildFromEnterElement((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTransactionImpl.class), 4, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("gnc_transaction" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 2;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  2 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  5 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  1 :
                        state = 4;
                        continue outer;
                    case  0 :
                        state = 1;
                        continue outer;
                    case  4 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        if (("gnc_transaction" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 4;
                            return ;
                        }
                        break;
                    case  6 :
                        if (("gnc_account" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 1;
                            return ;
                        }
                        break;
                    case  2 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  5 :
                        if (("version" == ___local)&&("" == ___uri)) {
                            _getGncAccount().add(((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncAccountTypeImpl) spawnChildFromEnterAttribute((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncAccountTypeImpl.class), 6, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                    case  1 :
                        state = 4;
                        continue outer;
                    case  0 :
                        state = 1;
                        continue outer;
                    case  4 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("version" == ___local)&&("" == ___uri)) {
                            _getGncTransaction().add(((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTransactionTypeImpl) spawnChildFromEnterAttribute((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncTransactionTypeImpl.class), 3, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  5 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  1 :
                        state = 4;
                        continue outer;
                    case  0 :
                        state = 1;
                        continue outer;
                    case  4 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  5 :
                            attIdx = context.getAttribute("", "version");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  1 :
                            state = 4;
                            continue outer;
                        case  0 :
                            state = 1;
                            continue outer;
                        case  4 :
                            revertToParentFromText(value);
                            return ;
                        case  2 :
                            attIdx = context.getAttribute("", "version");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
