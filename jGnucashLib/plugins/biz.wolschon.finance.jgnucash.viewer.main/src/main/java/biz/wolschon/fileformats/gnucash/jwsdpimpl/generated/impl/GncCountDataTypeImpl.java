//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl;

public class GncCountDataTypeImpl implements biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountDataType, com.sun.xml.bind.JAXBObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallableObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializable, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.ValidatableObject
{

    protected boolean has_Value;
    protected int _Value;
    protected java.lang.String _CdType;
    public final static java.lang.Class version = (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountDataType.class);
    }

    public int getValue() {
        return _Value;
    }

    public void setValue(int value) {
        _Value = value;
        has_Value = true;
    }

    public java.lang.String getCdType() {
        return _CdType;
    }

    public void setCdType(java.lang.String value) {
        _CdType = value;
    }

    public biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
        return new biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncCountDataTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Value) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Value"));
        }
        try {
            context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _Value)), "Value");
        } catch (java.lang.Exception e) {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
    }

    public void serializeAttributes(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Value) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Value"));
        }
        context.startAttribute("", "cd_type");
        try {
            context.text(((java.lang.String) _CdType), "CdType");
        } catch (java.lang.Exception e) {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
    }

    public void serializeURIs(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Value) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Value"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountDataType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004na"
+"met\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000 com.sun.msv.da"
+"tatype.xsd.IntType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.In"
+"tegerDerivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)Lcom/sun/msv/data"
+"type/xsd/XSDatatypeImpl;xr\u0000*com.sun.msv.datatype.xsd.Builtin"
+"AtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteTy"
+"pe\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0010L"
+"\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor"
+";xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0003intsr\u00005com.sun.msv."
+"datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com."
+"sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u0000*com"
+".sun.msv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.su"
+"n.msv.datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000\u0012Ljav"
+"a/lang/Object;xr\u00009com.sun.msv.datatype.xsd.DataTypeWithValue"
+"ConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataT"
+"ypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL"
+"\u0000\bbaseTypeq\u0000~\u0000\fL\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/C"
+"oncreteType;L\u0000\tfacetNameq\u0000~\u0000\u0010xq\u0000~\u0000\u000fppq\u0000~\u0000\u0017\u0000\u0001sr\u0000*com.sun.msv."
+"datatype.xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0019ppq\u0000~\u0000\u0017\u0000\u0000sr\u0000!"
+"com.sun.msv.datatype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u000bq\u0000~\u0000\u0013t\u0000\u0004lo"
+"ngq\u0000~\u0000\u0017sq\u0000~\u0000\u0018ppq\u0000~\u0000\u0017\u0000\u0001sq\u0000~\u0000\u001fppq\u0000~\u0000\u0017\u0000\u0000sr\u0000$com.sun.msv.datatyp"
+"e.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u000bq\u0000~\u0000\u0013t\u0000\u0007integerq\u0000~\u0000\u0017sr\u0000,co"
+"m.sun.msv.datatype.xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scal"
+"exr\u0000;com.sun.msv.datatype.xsd.DataTypeWithLexicalConstraintF"
+"acetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000\u001cppq\u0000~\u0000\u0017\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.N"
+"umberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\rq\u0000~\u0000\u0013t\u0000\u0007decimalq\u0000~\u0000\u0017q\u0000~\u0000-t\u0000\u000efracti"
+"onDigits\u0000\u0000\u0000\u0000q\u0000~\u0000\'t\u0000\fminInclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000"
+"\u0001J\u0000\u0005valuexr\u0000\u0010java.lang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000\'t\u0000\fma"
+"xInclusivesq\u0000~\u00001\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000\"q\u0000~\u00000sr\u0000\u0011java.lang.Integer\u0012\u00e2\u00a0\u00a4\u00f7"
+"\u0081\u00878\u0002\u0000\u0001I\u0000\u0005valuexq\u0000~\u00002\u0080\u0000\u0000\u0000q\u0000~\u0000\"q\u0000~\u00004sq\u0000~\u00006\u007f\u00ff\u00ff\u00ffsr\u00000com.sun.msv."
+"grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bc"
+"om.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0010L\u0000\fnam"
+"espaceURIq\u0000~\u0000\u0010xpq\u0000~\u0000\u0014q\u0000~\u0000\u0013sr\u0000 com.sun.msv.grammar.AttributeE"
+"xp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/"
+"NameClass;xq\u0000~\u0000\u0003ppsq\u0000~\u0000\u0006ppsr\u0000)com.sun.msv.datatype.xsd.Enume"
+"rationFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/util/Set;xq\u0000~\u0000\u001bt\u0000\u0000pq"
+"\u0000~\u0000\u0017\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.NmtokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
+"\"com.sun.msv.datatype.xsd.TokenType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.ms"
+"v.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000\rq\u0000"
+"~\u0000\u0013t\u0000\u0007NMTOKENq\u0000~\u0000\u0017\u0000q\u0000~\u0000Ht\u0000\u000benumerationsr\u0000\u0011java.util.HashSet\u00ba"
+"D\u0085\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000 ?@\u0000\u0000\u0000\u0000\u0000\rt\u0000\u000egnc:GncInvoicet\u0000\u000btransactiont\u0000\u000f"
+"gnc:GncTaxTablet\u0000\u000fgnc:GncEmployeet\u0000\fgnc:GncEntryt\u0000\fschedxact"
+"iont\u0000\rgnc:GncVendort\u0000\ngnc:GncJobt\u0000\tcommodityt\u0000\u000fgnc:GncCustom"
+"ert\u0000\u0004bookt\u0000\u0007accountt\u0000\u000fgnc:GncBillTermxq\u0000~\u0000:sq\u0000~\u0000;t\u0000\u000fNMTOKEN-"
+"derivedq\u0000~\u0000Dsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0010L\u0000\fnamespaceURIq\u0000~\u0000\u0010xr\u0000\u001dcom.sun.msv.gramm"
+"ar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0007cd_typeq\u0000~\u0000Dsr\u0000\"com.sun.msv.gram"
+"mar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/gram"
+"mar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Expr"
+"essionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006p"
+"arentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u0001\u0001pq\u0000~\u0000\u0005x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
            super(context, "-----");
        }

        protected Unmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncCountDataTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  4 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "cd_type");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _CdType = com.sun.xml.bind.WhiteSpaceProcessor.collapse(value);
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  4 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        attIdx = context.getAttribute("", "cd_type");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
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
                    case  4 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  0 :
                        if (("cd_type" == ___local)&&("" == ___uri)) {
                            state = 1;
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
                    case  4 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("cd_type" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "cd_type");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
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
                        case  4 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
                            eatText1(value);
                            state = 2;
                            return ;
                        case  0 :
                            attIdx = context.getAttribute("", "cd_type");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                eatText1(v);
                                state = 3;
                                continue outer;
                            }
                            break;
                        case  3 :
                            eatText2(value);
                            state = 4;
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Value = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_Value = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
