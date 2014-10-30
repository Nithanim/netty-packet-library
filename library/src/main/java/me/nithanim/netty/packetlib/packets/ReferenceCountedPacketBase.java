package me.nithanim.netty.packetlib.packets;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCounted;
import me.nithanim.netty.packetlib.processing.PacketProcessor;

public abstract class ReferenceCountedPacketBase<PACKET_PROCESSOR extends PacketProcessor> extends PacketBase<PACKET_PROCESSOR> implements ReferenceCounted{
    private final ReferenceCounted referenceCounter = new AbstractReferenceCounted() {
        @Override
        protected void deallocate() {
            ReferenceCountedPacketBase.this.deallocate();
        }
    };
    
    @Override
    public int refCnt() {
        return referenceCounter.refCnt();
    }
    
    @Override
    public ReferenceCounted retain() {
        referenceCounter.retain();
        return this;
    }
    
    @Override
    public ReferenceCounted retain(int increment) {
        referenceCounter.retain(increment);
        return this;
    }
    
    @Override
    public boolean release() {
        return referenceCounter.release();
    }
    
    @Override
    public boolean release(int decrement) {
        return referenceCounter.release(decrement);
    }
    
    protected abstract void deallocate();
}
