//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl;

public class BookElementsGncGncEntryImpl
    extends biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncGncEntryTypeImpl
    implements biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEntry, com.sun.xml.bind.RIElement, com.sun.xml.bind.JAXBObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallableObject, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializable, biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.ValidatableObject
{

    public final static java.lang.Class version = (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEntry.class);
    }

    public java.lang.String ____jaxb_ri____getNamespaceURI() {
        return "";
    }

    public java.lang.String ____jaxb_ri____getLocalName() {
        return "gnc_GncEntry";
    }

    public biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingEventHandler createUnmarshaller(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.UnmarshallingContext context) {
        return new biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.BookElementsGncGncEntryImpl.Unmarshaller(context);
    }

    public void serializeBody(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "gnc_GncEntry");
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
        return (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEntry.class);
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
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~\u0000\u0004ppsq\u0000~\u0000\u0007ppsr\u0000!com.s"
+"un.msv.grammar.InterleaveExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\bppsq\u0000~\u0000\u000bppsq\u0000~\u0000"
+"\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bpps"
+"q\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000"
+"\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u000bppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007pp"
+"sq\u0000~\u0000\u0000pp\u0000sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\bp"
+"psr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun."
+"msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0003xq\u0000~\u0000\u0004sr\u0000\u0011java.lan"
+"g.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.Attr"
+"ibuteExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0003L\u0000\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004q\u0000~\u0000,ps"
+"r\u00002com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004sq\u0000~\u0000+\u0001psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.s"
+"un.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004"
+"q\u0000~\u00001psr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tl"
+"ocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u00008xq\u0000~\u00003t\u0000Rbi"
+"z.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEnt"
+"ryType.EntryGuidTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-el"
+"ementssq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000"
+"\u0003L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0004ppsr\u0000\"com.sun."
+"msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatyp"
+"e.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.x"
+"sd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDat"
+"atypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u00008L\u0000\btypeNameq\u0000~\u00008L\u0000\nw"
+"hiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xp"
+"t\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.d"
+"atatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.s"
+"un.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com."
+"sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"\u0004ppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~"
+"\u00008L\u0000\fnamespaceURIq\u0000~\u00008xpq\u0000~\u0000Iq\u0000~\u0000Hsq\u0000~\u00007t\u0000\u0004typet\u0000)http://www"
+".w3.org/2001/XMLSchema-instanceq\u0000~\u00006sq\u0000~\u00007t\u0000\nentry_guidt\u0000\u0000sq"
+"\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000"
+"0q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000Rbiz.wolschon.fileformats.gnucash.jwsdpim"
+"pl.generated.GncGncEntryType.EntryDateTypeq\u0000~\u0000;sq\u0000~\u0000&ppsq\u0000~\u0000"
+"-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\nentry_dateq\u0000~\u0000Vsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000"
+"\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u00000q\u0000~\u00004q\u0000~\u00006s"
+"q\u0000~\u00007t\u0000Ubiz.wolschon.fileformats.gnucash.jwsdpimpl.generated"
+".GncGncEntryType.EntryEnteredTypeq\u0000~\u0000;sq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000"
+"~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\rentry_enteredq\u0000~\u0000Vsq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p"
+"\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000>q\u0000~\u0000,psr\u0000#com.sun.msv.datatype.xsd.StringType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000Cq\u0000~\u0000Ht\u0000\u0006stringsr\u00005com.sun.m"
+"sv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"K\u0001q\u0000~\u0000Nsq\u0000~\u0000Oq\u0000~\u0000uq\u0000~\u0000Hsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq"
+"\u0000~\u00007t\u0000\u0011entry_descriptionq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000"
+"\u0007ppsq\u0000~\u0000>ppsr\u0000)com.sun.msv.datatype.xsd.EnumerationFacet\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/util/Set;xr\u00009com.sun.msv.datatype.x"
+"sd.DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.ms"
+"v.datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000"
+"\u0012needValueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/xsd/X"
+"SDatatypeImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/Co"
+"ncreteType;L\u0000\tfacetNameq\u0000~\u00008xq\u0000~\u0000Eq\u0000~\u0000Vpq\u0000~\u0000w\u0000\u0000q\u0000~\u0000tq\u0000~\u0000tt\u0000\u000b"
+"enumerationsr\u0000\u0011java.util.HashSet\u00baD\u0085\u0095\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0003t"
+"\u0000\u0007Stundent\u0000\bMaterialt\u0000\u0007Auftragxq\u0000~\u0000Nsq\u0000~\u0000Ot\u0000\u000estring-derivedq"
+"\u0000~\u0000Vsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\fentry_action"
+"q\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000"
+"~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\tentry_qtyq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000"
+"\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~"
+"\u00000q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000Sbiz.wolschon.fileformats.gnucash.jwsdpi"
+"mpl.generated.GncGncEntryType.EntryBAcctTypeq\u0000~\u0000;sq\u0000~\u0000&ppsq\u0000"
+"~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\fentry_b-acctq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000"
+"&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq"
+"\u0000~\u00006sq\u0000~\u00007t\u0000\rentry_b-priceq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000"
+"~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u00000q\u0000~\u00004q\u0000~\u0000"
+"6sq\u0000~\u00007t\u0000Rbiz.wolschon.fileformats.gnucash.jwsdpimpl.generat"
+"ed.GncGncEntryType.EntryBillTypeq\u0000~\u0000;sq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~"
+"\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\nentry_billq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,"
+"p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000>ppsr\u0000 com.sun.msv.datatype.xsd.IntType\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.IntegerDerivedType\u0099\u00f1]\u0090&6k\u00be"
+"\u0002\u0000\u0001L\u0000\nbaseFacetsq\u0000~\u0000\u0085xq\u0000~\u0000Cq\u0000~\u0000Ht\u0000\u0003intq\u0000~\u0000Lsr\u0000*com.sun.msv.d"
+"atatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.data"
+"type.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000\u0012Ljava/lang/Obj"
+"ect;xq\u0000~\u0000\u0083ppq\u0000~\u0000L\u0000\u0001sr\u0000*com.sun.msv.datatype.xsd.MinInclusive"
+"Facet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u00c5ppq\u0000~\u0000L\u0000\u0000sr\u0000!com.sun.msv.datatype.xsd."
+"LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u00c1q\u0000~\u0000Ht\u0000\u0004longq\u0000~\u0000Lsq\u0000~\u0000\u00c4ppq\u0000~\u0000L\u0000\u0001sq\u0000"
+"~\u0000\u00c8ppq\u0000~\u0000L\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0000xq\u0000~\u0000\u00c1q\u0000~\u0000Ht\u0000\u0007integerq\u0000~\u0000Lsr\u0000,com.sun.msv.datatype.xsd.Fr"
+"actionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com.sun.msv.datatype"
+".xsd.DataTypeWithLexicalConstraintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000\u0084ppq\u0000"
+"~\u0000L\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"Cq\u0000~\u0000Ht\u0000\u0007decimalq\u0000~\u0000Lq\u0000~\u0000\u00d6t\u0000\u000efractionDigits\u0000\u0000\u0000\u0000q\u0000~\u0000\u00d0t\u0000\fminIn"
+"clusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.lang.Nu"
+"mber\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000\u00d0t\u0000\fmaxInclusivesq\u0000~\u0000\u00da\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq"
+"\u0000~\u0000\u00cbq\u0000~\u0000\u00d9sr\u0000\u0011java.lang.Integer\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005valuexq\u0000~\u0000\u00db\u0080\u0000\u0000\u0000q"
+"\u0000~\u0000\u00cbq\u0000~\u0000\u00ddsq\u0000~\u0000\u00df\u007f\u00ff\u00ff\u00ffq\u0000~\u0000Nsq\u0000~\u0000Oq\u0000~\u0000\u00c3q\u0000~\u0000Hsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,p"
+"q\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u000eentry_billableq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~"
+"\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000\u00bfsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000"
+"~\u00007t\u0000\u000fentry_b-taxableq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007pp"
+"q\u0000~\u0000\u00bfsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0013entry_b-tax"
+"includedq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000&pp"
+"sq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u000bentry_b-payq\u0000~\u0000Vq\u0000~\u00006sq\u0000"
+"~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000"
+"-q\u0000~\u0000,pq\u0000~\u00000q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000Wbiz.wolschon.fileformats.gnuc"
+"ash.jwsdpimpl.generated.GncGncEntryType.EntryBTaxtableTypeq\u0000"
+"~\u0000;sq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0010entry_b-taxta"
+"bleq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&pps"
+"q\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u00000q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000Sbiz.wolschon."
+"fileformats.gnucash.jwsdpimpl.generated.GncGncEntryType.Entr"
+"yIAcctTypeq\u0000~\u0000;sq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\fe"
+"ntry_i-acctq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000"
+"&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\rentry_i-priceq\u0000~\u0000Vq\u0000~"
+"\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000"
+"Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0010entry_i-discountq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000"
+"q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000"
+"0q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000Ubiz.wolschon.fileformats.gnucash.jwsdpim"
+"pl.generated.GncGncEntryType.EntryInvoiceTypeq\u0000~\u0000;sq\u0000~\u0000&ppsq"
+"\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\rentry_invoiceq\u0000~\u0000Vq\u0000~\u00006sq\u0000"
+"~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000"
+"Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0011entry_i-disc-typeq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000"
+",p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000rsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0010"
+"entry_i-disc-howq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000\u00bf"
+"sq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u000fentry_i-taxableq"
+"\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppq\u0000~\u0000\u00bfsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~"
+"\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0013entry_i-taxincludedq\u0000~\u0000Vq\u0000~\u00006sq\u0000~"
+"\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-"
+"q\u0000~\u0000,pq\u0000~\u00000q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000Wbiz.wolschon.fileformats.gnuca"
+"sh.jwsdpimpl.generated.GncGncEntryType.EntryITaxtableTypeq\u0000~"
+"\u0000;sq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u0010entry_i-taxtab"
+"leq\u0000~\u0000Vq\u0000~\u00006sq\u0000~\u0000&ppsq\u0000~\u0000\u0000q\u0000~\u0000,p\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0000pp\u0000sq\u0000~\u0000&ppsq"
+"\u0000~\u0000(q\u0000~\u0000,psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u00000q\u0000~\u00004q\u0000~\u00006sq\u0000~\u00007t\u0000>biz.wolschon.f"
+"ileformats.gnucash.jwsdpimpl.generated.SlotsTypeq\u0000~\u0000;sq\u0000~\u0000&p"
+"psq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\u000bentry_slotsq\u0000~\u0000Vq\u0000~\u00006sq"
+"\u0000~\u0000-ppq\u0000~\u0000rsq\u0000~\u00007t\u0000\u0007versionq\u0000~\u0000Vsq\u0000~\u0000&ppsq\u0000~\u0000-q\u0000~\u0000,pq\u0000~\u0000Aq\u0000~"
+"\u0000Qq\u0000~\u00006sq\u0000~\u00007t\u0000\fgnc_GncEntryq\u0000~\u0000Vsr\u0000\"com.sun.msv.grammar.Exp"
+"ressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/Exp"
+"ressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionP"
+"ool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000"
+"$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000s\u0001pq\u0000~\u0001Hq\u0000~\u0001Aq\u0000~\u0000\u00be"
+"q\u0000~\u0000\u00e9q\u0000~\u0000\u00f0q\u0000~\u0000\u0014q\u0000~\u0000\u000fq\u0000~\u0001jq\u0000~\u0001cq\u0000~\u0001Vq\u0000~\u0001Iq\u0000~\u0001Bq\u0000~\u0001;q\u0000~\u00014q\u0000~\u0001-"
+"q\u0000~\u0001 q\u0000~\u0001\u0019q\u0000~\u0001\u0012q\u0000~\u0001\u0005q\u0000~\u0000yq\u0000~\u0000\u0090q\u0000~\u0000\u0097q\u0000~\u0000\u00a4q\u0000~\u0000\u00abq\u0000~\u0000\u00b8q\u0000~\u0000\u00e3q\u0000~\u0000\u00ea"
+"q\u0000~\u0000\u00f1q\u0000~\u0000\u00f8q\u0000~\u0000<q\u0000~\u0000_q\u0000~\u0000kq\u0000~\u0000\u0011q\u0000~\u0001:q\u0000~\u00013q\u0000~\u0001\u001fq\u0000~\u0001\u0018q\u0000~\u0000qq\u0000~\u0000\u0096"
+"q\u0000~\u0000\u00aaq\u0000~\u0000\u00f7q\u0000~\u0000\u0018q\u0000~\u0000\u0010q\u0000~\u0000\u0019q\u0000~\u0000\u001bq\u0000~\u0000}q\u0000~\u0001Fq\u0000~\u0001?q\u0000~\u0000\u00bcq\u0000~\u0000\u00e7q\u0000~\u0000 "
+"q\u0000~\u0000\u00eeq\u0000~\u0001_q\u0000~\u0001^q\u0000~\u0001Qq\u0000~\u0001(q\u0000~\u0001\rq\u0000~\u0001\u0000q\u0000~\u0000\'q\u0000~\u0000Zq\u0000~\u0000fq\u0000~\u0000\u007fq\u0000~\u0000\u009f"
+"q\u0000~\u0000\u00b3q\u0000~\u0001Rq\u0000~\u0001)q\u0000~\u0000\u0017q\u0000~\u0001\u000eq\u0000~\u0001\u0001q\u0000~\u0000*q\u0000~\u0000\u001eq\u0000~\u0000[q\u0000~\u0000gq\u0000~\u0000\u00a0q\u0000~\u0000\u00b4"
+"q\u0000~\u0000\rq\u0000~\u0000\u000eq\u0000~\u0000\u0015q\u0000~\u0000\u0012q\u0000~\u0000\"q\u0000~\u0001Zq\u0000~\u0001Mq\u0000~\u0001$q\u0000~\u0001\tq\u0000~\u0000\u00fcq\u0000~\u0000\u009bq\u0000~\u0000\u00af"
+"q\u0000~\u00018q\u0000~\u00011q\u0000~\u0001\u001dq\u0000~\u0001\u0016q\u0000~\u0000oq\u0000~\u0000\u0094q\u0000~\u0000\u00a8q\u0000~\u0000\u00f5q\u0000~\u0000!q\u0000~\u0000\nq\u0000~\u0000\u001fq\u0000~\u0000\u001a"
+"q\u0000~\u0000\u009dq\u0000~\u0000\u00b1q\u0000~\u0001\\q\u0000~\u0001Oq\u0000~\u0001&q\u0000~\u0001\u000bq\u0000~\u0000\u00feq\u0000~\u0000$q\u0000~\u0000Xq\u0000~\u0000dq\u0000~\u0000\u0016q\u0000~\u0000\u001c"
+"q\u0000~\u0000\tq\u0000~\u0000\u001dq\u0000~\u0000\fq\u0000~\u0000\u0013x"));
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
            return biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.BookElementsGncGncEntryImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  1 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  0 :
                        if (("gnc_GncEntry" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
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
                    case  1 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  2 :
                        if (("gnc_GncEntry" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
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
                    case  1 :
                        if (("version" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterAttribute((((biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncGncEntryTypeImpl)biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.BookElementsGncGncEntryImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
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
                    case  1 :
                        attIdx = context.getAttribute("", "version");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
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
                        case  1 :
                            attIdx = context.getAttribute("", "version");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}