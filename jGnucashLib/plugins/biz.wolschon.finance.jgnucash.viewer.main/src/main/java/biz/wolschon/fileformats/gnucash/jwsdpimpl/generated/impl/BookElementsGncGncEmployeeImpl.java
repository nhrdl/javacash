//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl;

public class BookElementsGncGncEmployeeImpl
    extends biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncGncEmployeeTypeImpl
    implements biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEmployee, com.sun.xml.bind.RIElement, com.sun.xml.bind.JAXBObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallableObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializable, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.ValidatableObject
{

    public final static java.lang.Class version = (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEmployee.class);
    }

    public java.lang.String ____jaxb_ri____getNamespaceURI() {
        return "";
    }

    public java.lang.String ____jaxb_ri____getLocalName() {
        return "gnc_GncEmployee";
    }

    public biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
        return new biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.BookElementsGncGncEmployeeImpl.Unmarshaller(context);
    }

    public void serializeBody(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "gnc_GncEmployee");
        super.serializeURIs(context);
        context.endNamespaceDecls();
        super.serializeAttributes(context);
        context.endAttributes();
        super.serializeBody(context);
        context.endElement();
    }

    public void serializeAttributes(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEmployee.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv."
+"grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000"
+"\fcontentModelt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun."
+"msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Lj"
+"ava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0003xppp\u0000sr\u0000\u001fcom.sun.msv.gra"
+"mmar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~\u0000\u0004ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007pps"
+"q\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000"
+"\u0007ppsq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sr\u0000\u001dcom.sun.msv.grammar"
+".ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\bppsr\u0000 com.sun.msv.grammar.OneOrMo"
+"reExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L"
+"\u0000\u0003expq\u0000~\u0000\u0003xq\u0000~\u0000\u0004sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p"
+"sr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0003L\u0000"
+"\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004q\u0000~\u0000\u001epsr\u00002com.sun.msv.grammar.Expressio"
+"n$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004sq\u0000~\u0000\u001d\u0001psr\u0000 com.sun.ms"
+"v.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.Nam"
+"eClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$Epsilo"
+"nExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004q\u0000~\u0000#psr\u0000#com.sun.msv.grammar.Si"
+"mpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\f"
+"namespaceURIq\u0000~\u0000*xq\u0000~\u0000%t\u0000Xbiz.wolschon.fileformats.gnucash.j"
+"wsdpimpl.generated.GncGncEmployeeType.EmployeeGuidTypet\u0000+htt"
+"p://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001ep"
+"sr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relax"
+"ng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0003L\u0000\u0004namet\u0000\u001dLcom/sun/msv/ut"
+"il/StringPair;xq\u0000~\u0000\u0004ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnam"
+"espaceUriq\u0000~\u0000*L\u0000\btypeNameq\u0000~\u0000*L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/d"
+"atatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/"
+"XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProc"
+"essor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteS"
+"paceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression"
+"$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004ppsr\u0000\u001bcom.sun.msv.util.St"
+"ringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000*L\u0000\fnamespaceURIq\u0000~\u0000*xpq\u0000"
+"~\u0000;q\u0000~\u0000:sq\u0000~\u0000)t\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-ins"
+"tanceq\u0000~\u0000(sq\u0000~\u0000)t\u0000\remployee_guidt\u0000\u0000sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u00000pp"
+"sr\u0000\'com.sun.msv.datatype.xsd.MinLengthFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\tmin"
+"Lengthxr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstrai"
+"ntFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithF"
+"acet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTy"
+"pet\u0000)Lcom/sun/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fconcreteTyp"
+"et\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteType;L\u0000\tfacetNameq\u0000~\u0000*"
+"xq\u0000~\u00007q\u0000~\u0000Hpsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor"
+"$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000=\u0000\u0000sr\u0000#com.sun.msv.datatype.xsd.Str"
+"ingType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u00005q\u0000~\u0000:t\u0000\u0006stringq\u0000~\u0000S\u0001"
+"q\u0000~\u0000Ut\u0000\tminLength\u0000\u0000\u0000\u0001q\u0000~\u0000@sq\u0000~\u0000At\u0000\u000estring-derivedq\u0000~\u0000Hsq\u0000~\u0000\u0018"
+"ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u0011employee_usernameq\u0000~\u0000H"
+"sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u00000q\u0000~\u0000\u001epq\u0000~\u0000Uq\u0000~\u0000@sq\u0000~\u0000Aq\u0000~\u0000Vq\u0000~\u0000:sq\u0000~\u0000"
+"\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u000bemployee_idq\u0000~\u0000Hsq\u0000~\u0000"
+"\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001aq\u0000~\u0000\u001epsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u0000\"q\u0000"
+"~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u0000<biz.wolschon.fileformats.gnucash.jwsdpimpl."
+"generated.Addressq\u0000~\u0000-sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000"
+"~\u0000)t\u0000\remployee_addrq\u0000~\u0000Hsq\u0000~\u0000\u0018ppsq\u0000~\u0000\u0000q\u0000~\u0000\u001ep\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000`sq"
+"\u0000~\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u0011employee_languageq"
+"\u0000~\u0000Hq\u0000~\u0000(sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u00000ppsr\u0000 com.sun.msv.datatype.x"
+"sd.IntType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.IntegerDer"
+"ivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetsq\u0000~\u0000Oxq\u0000~\u00005q\u0000~\u0000:t\u0000\u0003intq\u0000~\u0000>s"
+"r\u0000*com.sun.msv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#"
+"com.sun.msv.datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet"
+"\u0000\u0012Ljava/lang/Object;xq\u0000~\u0000Mppq\u0000~\u0000>\u0000\u0001sr\u0000*com.sun.msv.datatype."
+"xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0081ppq\u0000~\u0000>\u0000\u0000sr\u0000!com.sun.m"
+"sv.datatype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000}q\u0000~\u0000:t\u0000\u0004longq\u0000~\u0000>sq"
+"\u0000~\u0000\u0080ppq\u0000~\u0000>\u0000\u0001sq\u0000~\u0000\u0084ppq\u0000~\u0000>\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.Int"
+"egerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000}q\u0000~\u0000:t\u0000\u0007integerq\u0000~\u0000>sr\u0000,com.sun.msv"
+".datatype.xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com."
+"sun.msv.datatype.xsd.DataTypeWithLexicalConstraintFacetT\u0090\u001c>\u001a"
+"zb\u00ea\u0002\u0000\u0000xq\u0000~\u0000Nppq\u0000~\u0000>\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.NumberType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u00005q\u0000~\u0000:t\u0000\u0007decimalq\u0000~\u0000>q\u0000~\u0000\u0092t\u0000\u000efractionDigits\u0000"
+"\u0000\u0000\u0000q\u0000~\u0000\u008ct\u0000\fminInclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005value"
+"xr\u0000\u0010java.lang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000\u008ct\u0000\fmaxInclusiv"
+"esq\u0000~\u0000\u0096\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000\u0087q\u0000~\u0000\u0095sr\u0000\u0011java.lang.Integer\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005"
+"valuexq\u0000~\u0000\u0097\u0080\u0000\u0000\u0000q\u0000~\u0000\u0087q\u0000~\u0000\u0099sq\u0000~\u0000\u009b\u007f\u00ff\u00ff\u00ffq\u0000~\u0000@sq\u0000~\u0000Aq\u0000~\u0000\u007fq\u0000~\u0000:sq\u0000~"
+"\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u000femployee_activeq\u0000~\u0000H"
+"sq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000`sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000"
+"~\u0000)t\u0000\u0010employee_workdayq\u0000~\u0000Hsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000`sq\u0000~\u0000\u0018ppsq\u0000"
+"~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\remployee_rateq\u0000~\u0000Hsq\u0000~\u0000\u0000pp\u0000"
+"sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001aq\u0000~\u0000\u001epsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u0000\"q\u0000~\u0000&q"
+"\u0000~\u0000(sq\u0000~\u0000)t\u0000\\biz.wolschon.fileformats.gnucash.jwsdpimpl.gene"
+"rated.GncGncEmployeeType.EmployeeCurrencyTypeq\u0000~\u0000-sq\u0000~\u0000\u0018ppsq"
+"\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u0011employee_currencyq\u0000~\u0000Hsq\u0000~"
+"\u0000\u0018ppsq\u0000~\u0000\u0000q\u0000~\u0000\u001ep\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001aq\u0000~\u0000\u001epsq\u0000~\u0000\u001f"
+"q\u0000~\u0000\u001epq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u0000Ybiz.wolschon.fileformats.gnuca"
+"sh.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCcardTypeq"
+"\u0000~\u0000-sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u000eemployee_cca"
+"rdq\u0000~\u0000Hq\u0000~\u0000(sq\u0000~\u0000\u0018ppsq\u0000~\u0000\u0000q\u0000~\u0000\u001ep\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0018ppsq"
+"\u0000~\u0000\u001aq\u0000~\u0000\u001epsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u0000\"q\u0000~\u0000&q\u0000~\u0000(sq\u0000~\u0000)t\u0000>biz.wolschon.f"
+"ileformats.gnucash.jwsdpimpl.generated.SlotsTypeq\u0000~\u0000-sq\u0000~\u0000\u0018p"
+"psq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\ncust_slotsq\u0000~\u0000Hq\u0000~\u0000(sq\u0000"
+"~\u0000\u001fppq\u0000~\u0000`sq\u0000~\u0000)t\u0000\u0007versionq\u0000~\u0000Hsq\u0000~\u0000\u0018ppsq\u0000~\u0000\u001fq\u0000~\u0000\u001epq\u0000~\u00003q\u0000~\u0000"
+"Cq\u0000~\u0000(sq\u0000~\u0000)t\u0000\u000fgnc_GncEmployeeq\u0000~\u0000Hsr\u0000\"com.sun.msv.grammar.E"
+"xpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/E"
+"xpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Expressio"
+"nPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parent"
+"t\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u00000\u0001pq\u0000~\u0000nq\u0000~\u0000uq\u0000~"
+"\u0000\u009fq\u0000~\u0000\u00a5q\u0000~\u0000\u00abq\u0000~\u0000\u0010q\u0000~\u0000\u00b7q\u0000~\u0000\u00c4q\u0000~\u0000\u00d1q\u0000~\u0000\u0013q\u0000~\u0000\u00d8q\u0000~\u0000\u0012q\u0000~\u0000\u0011q\u0000~\u0000\fq\u0000~"
+"\u0000\nq\u0000~\u0000Jq\u0000~\u0000_q\u0000~\u0000tq\u0000~\u0000\u00a4q\u0000~\u0000\u00aaq\u0000~\u0000\u0014q\u0000~\u0000\u00bbq\u0000~\u0000\u001cq\u0000~\u0000jq\u0000~\u0000\u00b3q\u0000~\u0000\u00c0q\u0000~"
+"\u0000\u00cdq\u0000~\u0000\u00c8q\u0000~\u0000\u000bq\u0000~\u0000\u000fq\u0000~\u0000\u0016q\u0000~\u0000gq\u0000~\u0000\u00b0q\u0000~\u0000\u00bdq\u0000~\u0000\u00caq\u0000~\u0000\u0019q\u0000~\u0000iq\u0000~\u0000\u00b2q\u0000~"
+"\u0000\u00bfq\u0000~\u0000rq\u0000~\u0000\u00ccq\u0000~\u0000\u000eq\u0000~\u0000zq\u0000~\u0000\tq\u0000~\u0000\rq\u0000~\u0000.q\u0000~\u0000Zq\u0000~\u0000bx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
            super(context, "----");
        }

        protected Unmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.BookElementsGncGncEmployeeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("gnc_GncEmployee" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  1 :
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
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  2 :
                        if (("gnc_GncEmployee" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
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
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        if (("version" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterAttribute((((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncGncEmployeeTypeImpl)biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.BookElementsGncGncEmployeeImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname);
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
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
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
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
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
