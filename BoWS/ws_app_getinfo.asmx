<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="fn_ws_get_active_payment_type">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="snum" type="s:long" />
            <s:element minOccurs="1" maxOccurs="1" name="is_back_office" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="security_key" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="fn_ws_get_active_payment_typeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="fn_ws_get_active_payment_typeResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="fn_ws_get_active_payment_typeSoapIn">
    <wsdl:part name="parameters" element="tns:fn_ws_get_active_payment_type" />
  </wsdl:message>
  <wsdl:message name="fn_ws_get_active_payment_typeSoapOut">
    <wsdl:part name="parameters" element="tns:fn_ws_get_active_payment_typeResponse" />
  </wsdl:message>
  <wsdl:portType name="ws_app_getinfoSoap">
    <wsdl:operation name="fn_ws_get_active_payment_type">
      <wsdl:input message="tns:fn_ws_get_active_payment_typeSoapIn" />
      <wsdl:output message="tns:fn_ws_get_active_payment_typeSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="ws_app_getinfoSoap" type="tns:ws_app_getinfoSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="fn_ws_get_active_payment_type">
      <soap:operation soapAction="http://tempuri.org/fn_ws_get_active_payment_type" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ws_app_getinfoSoap12" type="tns:ws_app_getinfoSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="fn_ws_get_active_payment_type">
      <soap12:operation soapAction="http://tempuri.org/fn_ws_get_active_payment_type" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="ws_app_getinfo">
    <wsdl:port name="ws_app_getinfoSoap" binding="tns:ws_app_getinfoSoap">
      <soap:address location="http://servicios.acklins.net/ws_app_getinfo.asmx" />
    </wsdl:port>
    <wsdl:port name="ws_app_getinfoSoap12" binding="tns:ws_app_getinfoSoap12">
      <soap12:address location="http://servicios.acklins.net/ws_app_getinfo.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>