package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public final class MergingSequence<T1, T2, V> implements Sequence<V> {
    private final Sequence<T1> sequence1;
    private final Sequence<T2> sequence2;
    private final Function2<T1, T2, V> transform;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.sequences.Sequence<? extends T1> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.sequences.Sequence<? extends T2> */
    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: kotlin.jvm.functions.Function2<? super T1, ? super T2, ? extends V> */
    /* JADX WARN: Multi-variable type inference failed */
    public MergingSequence(Sequence<? extends T1> sequence, Sequence<? extends T2> sequence3, Function2<? super T1, ? super T2, ? extends V> function2) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence1");
        Intrinsics.checkParameterIsNotNull(sequence3, "sequence2");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        this.sequence1 = sequence;
        this.sequence2 = sequence3;
        this.transform = function2;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<V> iterator() {
        return new MergingSequence$iterator$1(this);
    }
}
