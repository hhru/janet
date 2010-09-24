/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */


package ru.hh.search.janet.example;

public final class Calculator {
  private Calculator() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class CalcRequest extends
      com.google.protobuf.GeneratedMessage {
    // Use CalcRequest.newBuilder() to construct.
    private CalcRequest() {}
    
    private static final CalcRequest defaultInstance = new CalcRequest();
    public static CalcRequest getDefaultInstance() {
      return defaultInstance;
    }
    
    public CalcRequest getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Calculator.internal_static_CalcRequest_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Calculator.internal_static_CalcRequest_fieldAccessorTable;
    }
    
    // required int32 op1 = 1;
    public static final int OP1_FIELD_NUMBER = 1;
    private boolean hasOp1;
    private int op1_ = 0;
    public boolean hasOp1() { return hasOp1; }
    public int getOp1() { return op1_; }
    
    // required int32 op2 = 2;
    public static final int OP2_FIELD_NUMBER = 2;
    private boolean hasOp2;
    private int op2_ = 0;
    public boolean hasOp2() { return hasOp2; }
    public int getOp2() { return op2_; }
    
    public final boolean isInitialized() {
      if (!hasOp1) return false;
      if (!hasOp2) return false;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (hasOp1()) {
        output.writeInt32(1, getOp1());
      }
      if (hasOp2()) {
        output.writeInt32(2, getOp2());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasOp1()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, getOp1());
      }
      if (hasOp2()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, getOp2());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static Calculator.CalcRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeDelimitedFrom(input).buildParsed();
    }
    public static Calculator.CalcRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeDelimitedFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static Calculator.CalcRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(Calculator.CalcRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private Calculator.CalcRequest result;
      
      // Construct using Calculator.CalcRequest.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new Calculator.CalcRequest();
        return builder;
      }
      
      protected Calculator.CalcRequest internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new Calculator.CalcRequest();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Calculator.CalcRequest.getDescriptor();
      }
      
      public Calculator.CalcRequest getDefaultInstanceForType() {
        return Calculator.CalcRequest.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public Calculator.CalcRequest build() {
        if (result != null && !isInitialized()) {
          throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private Calculator.CalcRequest buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public Calculator.CalcRequest buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        Calculator.CalcRequest returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Calculator.CalcRequest) {
          return mergeFrom((Calculator.CalcRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(Calculator.CalcRequest other) {
        if (other == Calculator.CalcRequest.getDefaultInstance()) return this;
        if (other.hasOp1()) {
          setOp1(other.getOp1());
        }
        if (other.hasOp2()) {
          setOp2(other.getOp2());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 8: {
              setOp1(input.readInt32());
              break;
            }
            case 16: {
              setOp2(input.readInt32());
              break;
            }
          }
        }
      }
      
      
      // required int32 op1 = 1;
      public boolean hasOp1() {
        return result.hasOp1();
      }
      public int getOp1() {
        return result.getOp1();
      }
      public Builder setOp1(int value) {
        result.hasOp1 = true;
        result.op1_ = value;
        return this;
      }
      public Builder clearOp1() {
        result.hasOp1 = false;
        result.op1_ = 0;
        return this;
      }
      
      // required int32 op2 = 2;
      public boolean hasOp2() {
        return result.hasOp2();
      }
      public int getOp2() {
        return result.getOp2();
      }
      public Builder setOp2(int value) {
        result.hasOp2 = true;
        result.op2_ = value;
        return this;
      }
      public Builder clearOp2() {
        result.hasOp2 = false;
        result.op2_ = 0;
        return this;
      }
    }
    
    static {
      Calculator.getDescriptor();
    }
    
    static {
      Calculator.internalForceInit();
    }
  }
  
  public static final class CalcResponse extends
      com.google.protobuf.GeneratedMessage {
    // Use CalcResponse.newBuilder() to construct.
    private CalcResponse() {}
    
    private static final CalcResponse defaultInstance = new CalcResponse();
    public static CalcResponse getDefaultInstance() {
      return defaultInstance;
    }
    
    public CalcResponse getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Calculator.internal_static_CalcResponse_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Calculator.internal_static_CalcResponse_fieldAccessorTable;
    }
    
    // required int32 result = 1;
    public static final int RESULT_FIELD_NUMBER = 1;
    private boolean hasResult;
    private int result_ = 0;
    public boolean hasResult() { return hasResult; }
    public int getResult() { return result_; }
    
    public final boolean isInitialized() {
      if (!hasResult) return false;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (hasResult()) {
        output.writeInt32(1, getResult());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasResult()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, getResult());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static Calculator.CalcResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeDelimitedFrom(input).buildParsed();
    }
    public static Calculator.CalcResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeDelimitedFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static Calculator.CalcResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(Calculator.CalcResponse prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private Calculator.CalcResponse result;
      
      // Construct using Calculator.CalcResponse.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new Calculator.CalcResponse();
        return builder;
      }
      
      protected Calculator.CalcResponse internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new Calculator.CalcResponse();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Calculator.CalcResponse.getDescriptor();
      }
      
      public Calculator.CalcResponse getDefaultInstanceForType() {
        return Calculator.CalcResponse.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public Calculator.CalcResponse build() {
        if (result != null && !isInitialized()) {
          throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private Calculator.CalcResponse buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public Calculator.CalcResponse buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        Calculator.CalcResponse returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Calculator.CalcResponse) {
          return mergeFrom((Calculator.CalcResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(Calculator.CalcResponse other) {
        if (other == Calculator.CalcResponse.getDefaultInstance()) return this;
        if (other.hasResult()) {
          setResult(other.getResult());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 8: {
              setResult(input.readInt32());
              break;
            }
          }
        }
      }
      
      
      // required int32 result = 1;
      public boolean hasResult() {
        return result.hasResult();
      }
      public int getResult() {
        return result.getResult();
      }
      public Builder setResult(int value) {
        result.hasResult = true;
        result.result_ = value;
        return this;
      }
      public Builder clearResult() {
        result.hasResult = false;
        result.result_ = 0;
        return this;
      }
    }
    
    static {
      Calculator.getDescriptor();
    }
    
    static {
      Calculator.internalForceInit();
    }
  }
  
  public static abstract class CalcService
      implements com.google.protobuf.Service {
    protected CalcService() {}
    
    public interface Interface {
      public abstract void add(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
      
      public abstract void subtract(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
      
      public abstract void multiply(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
      
      public abstract void divide(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
      
    }
    
    public static com.google.protobuf.Service newReflectiveService(
        final Interface impl) {
      return new CalcService() {
        @Override
        public  void add(
            com.google.protobuf.RpcController controller,
            Calculator.CalcRequest request,
            com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
          impl.add(controller, request, done);
        }
        
        @Override
        public  void subtract(
            com.google.protobuf.RpcController controller,
            Calculator.CalcRequest request,
            com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
          impl.subtract(controller, request, done);
        }
        
        @Override
        public  void multiply(
            com.google.protobuf.RpcController controller,
            Calculator.CalcRequest request,
            com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
          impl.multiply(controller, request, done);
        }
        
        @Override
        public  void divide(
            com.google.protobuf.RpcController controller,
            Calculator.CalcRequest request,
            com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
          impl.divide(controller, request, done);
        }
        
      };
    }
    
    public static com.google.protobuf.BlockingService
        newReflectiveBlockingService(final BlockingInterface impl) {
      return new com.google.protobuf.BlockingService() {
        public final com.google.protobuf.Descriptors.ServiceDescriptor
            getDescriptorForType() {
          return getDescriptor();
        }
        
        public final com.google.protobuf.Message callBlockingMethod(
            com.google.protobuf.Descriptors.MethodDescriptor method,
            com.google.protobuf.RpcController controller,
            com.google.protobuf.Message request)
            throws com.google.protobuf.ServiceException {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.callBlockingMethod() given method descriptor for " +
              "wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return impl.add(controller, (Calculator.CalcRequest)request);
            case 1:
              return impl.subtract(controller, (Calculator.CalcRequest)request);
            case 2:
              return impl.multiply(controller, (Calculator.CalcRequest)request);
            case 3:
              return impl.divide(controller, (Calculator.CalcRequest)request);
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }
        
        public final com.google.protobuf.Message
            getRequestPrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.getRequestPrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return Calculator.CalcRequest.getDefaultInstance();
            case 1:
              return Calculator.CalcRequest.getDefaultInstance();
            case 2:
              return Calculator.CalcRequest.getDefaultInstance();
            case 3:
              return Calculator.CalcRequest.getDefaultInstance();
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }
        
        public final com.google.protobuf.Message
            getResponsePrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
              "Service.getResponsePrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return Calculator.CalcResponse.getDefaultInstance();
            case 1:
              return Calculator.CalcResponse.getDefaultInstance();
            case 2:
              return Calculator.CalcResponse.getDefaultInstance();
            case 3:
              return Calculator.CalcResponse.getDefaultInstance();
            default:
              throw new java.lang.AssertionError("Can't get here.");
          }
        }
        
      };
    }
    
    public abstract void add(
        com.google.protobuf.RpcController controller,
        Calculator.CalcRequest request,
        com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
    
    public abstract void subtract(
        com.google.protobuf.RpcController controller,
        Calculator.CalcRequest request,
        com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
    
    public abstract void multiply(
        com.google.protobuf.RpcController controller,
        Calculator.CalcRequest request,
        com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
    
    public abstract void divide(
        com.google.protobuf.RpcController controller,
        Calculator.CalcRequest request,
        com.google.protobuf.RpcCallback<Calculator.CalcResponse> done);
    
    public static final
        com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptor() {
      return Calculator.getDescriptor().getServices().get(0);
    }
    public final com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    
    public final void callMethod(
        com.google.protobuf.Descriptors.MethodDescriptor method,
        com.google.protobuf.RpcController controller,
        com.google.protobuf.Message request,
        com.google.protobuf.RpcCallback<
          com.google.protobuf.Message> done) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.callMethod() given method descriptor for wrong " +
          "service type.");
      }
      switch(method.getIndex()) {
        case 0:
          this.add(controller, (Calculator.CalcRequest)request,
            com.google.protobuf.RpcUtil.<Calculator.CalcResponse>specializeCallback(
              done));
          return;
        case 1:
          this.subtract(controller, (Calculator.CalcRequest)request,
            com.google.protobuf.RpcUtil.<Calculator.CalcResponse>specializeCallback(
              done));
          return;
        case 2:
          this.multiply(controller, (Calculator.CalcRequest)request,
            com.google.protobuf.RpcUtil.<Calculator.CalcResponse>specializeCallback(
              done));
          return;
        case 3:
          this.divide(controller, (Calculator.CalcRequest)request,
            com.google.protobuf.RpcUtil.<Calculator.CalcResponse>specializeCallback(
              done));
          return;
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }
    
    public final com.google.protobuf.Message
        getRequestPrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.getRequestPrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return Calculator.CalcRequest.getDefaultInstance();
        case 1:
          return Calculator.CalcRequest.getDefaultInstance();
        case 2:
          return Calculator.CalcRequest.getDefaultInstance();
        case 3:
          return Calculator.CalcRequest.getDefaultInstance();
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }
    
    public final com.google.protobuf.Message
        getResponsePrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "Service.getResponsePrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return Calculator.CalcResponse.getDefaultInstance();
        case 1:
          return Calculator.CalcResponse.getDefaultInstance();
        case 2:
          return Calculator.CalcResponse.getDefaultInstance();
        case 3:
          return Calculator.CalcResponse.getDefaultInstance();
        default:
          throw new java.lang.AssertionError("Can't get here.");
      }
    }
    
    public static Stub newStub(
        com.google.protobuf.RpcChannel channel) {
      return new Stub(channel);
    }
    
    public static final class Stub extends Calculator.CalcService implements Interface {
      private Stub(com.google.protobuf.RpcChannel channel) {
        this.channel = channel;
      }
      
      private final com.google.protobuf.RpcChannel channel;
      
      public com.google.protobuf.RpcChannel getChannel() {
        return channel;
      }
      
      public  void add(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            Calculator.CalcResponse.class,
            Calculator.CalcResponse.getDefaultInstance()));
      }
      
      public  void subtract(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(1),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            Calculator.CalcResponse.class,
            Calculator.CalcResponse.getDefaultInstance()));
      }
      
      public  void multiply(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(2),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            Calculator.CalcResponse.class,
            Calculator.CalcResponse.getDefaultInstance()));
      }
      
      public  void divide(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request,
          com.google.protobuf.RpcCallback<Calculator.CalcResponse> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(3),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            Calculator.CalcResponse.class,
            Calculator.CalcResponse.getDefaultInstance()));
      }
    }
    
    public static BlockingInterface newBlockingStub(
        com.google.protobuf.BlockingRpcChannel channel) {
      return new BlockingStub(channel);
    }
    
    public interface BlockingInterface {
      public Calculator.CalcResponse add(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException;
      
      public Calculator.CalcResponse subtract(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException;
      
      public Calculator.CalcResponse multiply(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException;
      
      public Calculator.CalcResponse divide(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException;
    }
    
    private static final class BlockingStub implements BlockingInterface {
      private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
        this.channel = channel;
      }
      
      private final com.google.protobuf.BlockingRpcChannel channel;
      
      public Calculator.CalcResponse add(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException {
        return (Calculator.CalcResponse) channel.callBlockingMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance());
      }
      
      
      public Calculator.CalcResponse subtract(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException {
        return (Calculator.CalcResponse) channel.callBlockingMethod(
          getDescriptor().getMethods().get(1),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance());
      }
      
      
      public Calculator.CalcResponse multiply(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException {
        return (Calculator.CalcResponse) channel.callBlockingMethod(
          getDescriptor().getMethods().get(2),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance());
      }
      
      
      public Calculator.CalcResponse divide(
          com.google.protobuf.RpcController controller,
          Calculator.CalcRequest request)
          throws com.google.protobuf.ServiceException {
        return (Calculator.CalcResponse) channel.callBlockingMethod(
          getDescriptor().getMethods().get(3),
          controller,
          request,
          Calculator.CalcResponse.getDefaultInstance());
      }
      
    }
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_CalcRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CalcRequest_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_CalcResponse_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CalcResponse_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rexample.proto\"\'\n\013CalcRequest\022\013\n\003op1\030\001 " +
      "\002(\005\022\013\n\003op2\030\002 \002(\005\"\036\n\014CalcResponse\022\016\n\006resu" +
      "lt\030\001 \002(\0052\252\001\n\013CalcService\022\"\n\003Add\022\014.CalcRe" +
      "quest\032\r.CalcResponse\022\'\n\010Subtract\022\014.CalcR" +
      "equest\032\r.CalcResponse\022\'\n\010Multiply\022\014.Calc" +
      "Request\032\r.CalcResponse\022%\n\006Divide\022\014.CalcR" +
      "equest\032\r.CalcResponseB3\n%com.hh." +
      "search.janet.exampleB\nCalculator"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_CalcRequest_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_CalcRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_CalcRequest_descriptor,
              new java.lang.String[] { "Op1", "Op2", },
              Calculator.CalcRequest.class,
              Calculator.CalcRequest.Builder.class);
          internal_static_CalcResponse_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_CalcResponse_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_CalcResponse_descriptor,
              new java.lang.String[] { "Result", },
              Calculator.CalcResponse.class,
              Calculator.CalcResponse.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  public static void internalForceInit() {}
}
