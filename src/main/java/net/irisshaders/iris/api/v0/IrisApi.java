package net.irisshaders.iris.api.v0;

public interface IrisApi {
    static IrisApi getInstance() {
        return IrisApiDummy.INSTANCE;
    }

    boolean isShaderPackInUse();

    class IrisApiDummy implements IrisApi {
        static final IrisApi INSTANCE = new IrisApiDummy();
        @Override
        public boolean isShaderPackInUse() {
            return false;
        }
    }
}
