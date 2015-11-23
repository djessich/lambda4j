/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.BooleanBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.ByteBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.CharBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.DoubleBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.FloatBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.IntBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.LongBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.ShortBiConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjBooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjCharConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjDoubleConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjFloatConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjIntConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjLongConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjShortConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjBooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjCharConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjFloatConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjShortConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.BooleanTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.ByteTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.CharTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.DoubleTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.FloatTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.IntTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.LongTriConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.ShortTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.bi.BooleanBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.ByteBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.CharBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.DoubleBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.FloatBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.IntBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.LongBiFunction;
import at.gridtec.lambda4j.function.primitives.bi.ShortBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiDoubleToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiDoubleToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiDoubleToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiDoubleToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiDoubleToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiDoubleToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiLongToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiLongToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiLongToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiLongToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiLongToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiLongToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriDoubleToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriDoubleToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriDoubleToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriDoubleToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriDoubleToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriDoubleToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriIntToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriIntToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriIntToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriIntToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriIntToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriIntToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriLongToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriLongToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriLongToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriLongToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriLongToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriLongToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjBooleanFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjByteFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjCharFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjDoubleFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjFloatFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjIntFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjLongFunction;
import at.gridtec.lambda4j.function.primitives.obj.BiObjShortFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjBooleanFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjByteFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjCharFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjDoubleFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjFloatFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjIntFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjLongFunction;
import at.gridtec.lambda4j.function.primitives.obj.ObjShortFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToByteBiObjByteFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToByteObjByteFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToCharBiObjCharFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToCharObjCharFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToDoubleBiObjDoubleFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToDoubleObjDoubleFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToFloatBiObjFloatFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToFloatObjFloatFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToIntBiObjIntFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToIntObjIntFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToLongBiObjLongFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToLongObjLongFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToShortBiObjShortFunction;
import at.gridtec.lambda4j.function.primitives.obj.ToShortObjShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToByteBiFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToCharBiFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToFloatBiFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToShortBiFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToByteTriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToCharTriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToDoubleTriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToFloatTriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToIntTriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToLongTriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToShortTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.BooleanTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.ByteTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.CharTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.DoubleTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.FloatTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.IntTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.LongTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.ShortTriFunction;
import at.gridtec.lambda4j.predicates.TriPredicate;
import at.gridtec.lambda4j.predicates.primitives.BytePredicate;
import at.gridtec.lambda4j.predicates.primitives.CharPredicate;
import at.gridtec.lambda4j.predicates.primitives.FloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.ShortPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.ByteBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.CharBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.DoubleBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.FloatBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.IntBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.LongBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.ShortBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjBytePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjCharPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjDoublePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjFloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjIntPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjLongPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjShortPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjBytePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjCharPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjDoublePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjFloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjIntPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjLongPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjShortPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.ByteTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.CharTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.DoubleTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.FloatTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.IntTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.LongTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.ShortTriPredicate;
import at.gridtec.lambda4j.supplier.ByteSupplier;
import at.gridtec.lambda4j.supplier.CharSupplier;
import at.gridtec.lambda4j.supplier.FloatSupplier;
import at.gridtec.lambda4j.supplier.ShortSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

/**
 * This class provides utility methods, which pertain to all {@link FunctionalInterface}s representing a function.
 */
@SuppressWarnings("unused")
public final class Functions {

    /**
     * Private Constructor to prevent instantiation.
     */
    private Functions() {

    }

    /**
     * Calls the given {@link Function} with the given arguments and returns its result.
     *
     * @param <T> The type of the argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code Function}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T, R> R call(@Nonnull final Function<? super T, ? extends R> function, T t) {
        Objects.requireNonNull(function);
        return function.apply(t);
    }

    /**
     * Calls the given {@link BiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code BiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T, U, R> R call(@Nonnull final BiFunction<? super T, ? super U, ? extends R> function, T t, U u) {
        Objects.requireNonNull(function);
        return function.apply(t, u);
    }

    /**
     * Calls the given {@link TriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code TriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V, R> R call(@Nonnull final TriFunction<? super T, ? super U, ? super V, ? extends R> function,
            T t, U u, V v) {
        return TriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link BooleanFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final BooleanFunction<? extends R> function, boolean value) {
        return BooleanFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final ByteFunction<? extends R> function, byte value) {
        return ByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final CharFunction<? extends R> function, char value) {
        return CharFunction.call(function, value);
    }

    /**
     * Calls the given {@link DoubleFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <R> R call(@Nonnull final DoubleFunction<? extends R> function, double value) {
        Objects.requireNonNull(function);
        return function.apply(value);
    }

    /**
     * Calls the given {@link FloatFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final FloatFunction<? extends R> function, float value) {
        return FloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link IntFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <R> R call(@Nonnull final IntFunction<? extends R> function, int value) {
        Objects.requireNonNull(function);
        return function.apply(value);
    }

    /**
     * Calls the given {@link LongFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <R> R call(@Nonnull final LongFunction<? extends R> function, long value) {
        Objects.requireNonNull(function);
        return function.apply(value);
    }

    /**
     * Calls the given {@link ShortFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final ShortFunction<? extends R> function, short value) {
        return ShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BooleanBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final BooleanBiFunction<? extends R> function, boolean value1, boolean value2) {
        return BooleanBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link ByteBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code ByteBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final ByteBiFunction<? extends R> function, byte value1, byte value2) {
        return ByteBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link CharBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code CharBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final CharBiFunction<? extends R> function, char value1, char value2) {
        return CharBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link DoubleBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code DoubleBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final DoubleBiFunction<? extends R> function, double value1, double value2) {
        return DoubleBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link FloatBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code FloatBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final FloatBiFunction<? extends R> function, float value1, float value2) {
        return FloatBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link IntBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code IntBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final IntBiFunction<? extends R> function, int value1, int value2) {
        return IntBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link LongBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code LongBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final LongBiFunction<? extends R> function, long value1, long value2) {
        return LongBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link ShortBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code ShortBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final ShortBiFunction<? extends R> function, short value1, short value2) {
        return ShortBiFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BooleanTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code BooleanTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final BooleanTriFunction<? extends R> function, boolean value1, boolean value2,
            boolean value3) {
        return BooleanTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link ByteTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code ByteTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final ByteTriFunction<? extends R> function, byte value1, byte value2,
            byte value3) {
        return ByteTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link CharTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code CharTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final CharTriFunction<? extends R> function, char value1, char value2,
            char value3) {
        return CharTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link DoubleTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code DoubleTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final DoubleTriFunction<? extends R> function, double value1, double value2,
            double value3) {
        return DoubleTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link FloatTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code FloatTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final FloatTriFunction<? extends R> function, float value1, float value2,
            float value3) {
        return FloatTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link IntTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code IntTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final IntTriFunction<? extends R> function, int value1, int value2, int value3) {
        return IntTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link LongTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code LongTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final LongTriFunction<? extends R> function, long value1, long value2,
            long value3) {
        return LongTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link ShortTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code ShortTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <R> R call(@Nonnull final ShortTriFunction<? extends R> function, short value1, short value2,
            short value3) {
        return ShortTriFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link ToByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> byte call(@Nonnull final ToByteFunction<? super T> function, T t) {
        return ToByteFunction.call(function, t);
    }

    /**
     * Calls the given {@link ToCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> char call(@Nonnull final ToCharFunction<? super T> function, T t) {
        return ToCharFunction.call(function, t);
    }

    /**
     * Calls the given {@link ToDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T> double call(@Nonnull final ToDoubleFunction<? super T> function, T t) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(t);
    }

    /**
     * Calls the given {@link ToFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> float call(@Nonnull final ToFloatFunction<? super T> function, T t) {
        return ToFloatFunction.call(function, t);
    }

    /**
     * Calls the given {@link ToIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T> int call(@Nonnull final ToIntFunction<? super T> function, T t) {
        Objects.requireNonNull(function);
        return function.applyAsInt(t);
    }

    /**
     * Calls the given {@link ToLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T> long call(@Nonnull final ToLongFunction<? super T> function, T t) {
        Objects.requireNonNull(function);
        return function.applyAsLong(t);
    }

    /**
     * Calls the given {@link ToShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> short call(@Nonnull final ToShortFunction<? super T> function, T t) {
        return ToShortFunction.call(function, t);
    }

    /**
     * Calls the given {@link ToByteBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToByteBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> byte call(@Nonnull final ToByteBiFunction<? super T, ? super U> function, T t, U u) {
        return ToByteBiFunction.call(function, t, u);
    }

    /**
     * Calls the given {@link ToCharBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToCharBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> char call(@Nonnull final ToCharBiFunction<? super T, ? super U> function, T t, U u) {
        return ToCharBiFunction.call(function, t, u);
    }

    /**
     * Calls the given {@link ToDoubleBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToDoubleBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T, U> double call(@Nonnull final ToDoubleBiFunction<? super T, ? super U> function, T t, U u) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(t, u);
    }

    /**
     * Calls the given {@link ToFloatBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToFloatBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> float call(@Nonnull final ToFloatBiFunction<? super T, ? super U> function, T t, U u) {
        return ToFloatBiFunction.call(function, t, u);
    }

    /**
     * Calls the given {@link ToShortBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToShortBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T, U> int call(@Nonnull final ToIntBiFunction<? super T, ? super U> function, T t, U u) {
        Objects.requireNonNull(function);
        return function.applyAsInt(t, u);
    }

    /**
     * Calls the given {@link ToLongBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToLongBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    public static <T, U> long call(@Nonnull final ToLongBiFunction<? super T, ? super U> function, T t, U u) {
        Objects.requireNonNull(function);
        return function.applyAsLong(t, u);
    }

    /**
     * Calls the given {@link ToShortBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ToShortBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> short call(@Nonnull final ToShortBiFunction<? super T, ? super U> function, T t, U u) {
        return ToShortBiFunction.call(function, t, u);
    }

    /**
     * Calls the given {@link ToByteTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToByteTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> byte call(@Nonnull final ToByteTriFunction<? super T, ? super U, ? super V> function, T t,
            U u, V v) {
        return ToByteTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ToCharTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToCharTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> char call(@Nonnull final ToCharTriFunction<? super T, ? super U, ? super V> function, T t,
            U u, V v) {
        return ToCharTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ToDoubleTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToDoubleTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> double call(@Nonnull final ToDoubleTriFunction<? super T, ? super U, ? super V> function,
            T t, U u, V v) {
        return ToDoubleTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ToFloatTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToFloatTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> float call(@Nonnull final ToFloatTriFunction<? super T, ? super U, ? super V> function, T t,
            U u, V v) {
        return ToFloatTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ToIntTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToIntTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> int call(@Nonnull final ToIntTriFunction<? super T, ? super U, ? super V> function, T t,
            U u, V v) {
        return ToIntTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ToLongTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToLongTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> long call(@Nonnull final ToLongTriFunction<? super T, ? super U, ? super V> function, T t,
            U u, V v) {
        return ToLongTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ToShortTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToShortTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> short call(@Nonnull final ToShortTriFunction<? super T, ? super U, ? super V> function, T t,
            U u, V v) {
        return ToShortTriFunction.call(function, t, u, v);
    }

    /**
     * Calls the given {@link ObjBooleanFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjBooleanFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjBooleanFunction<? super T, ? extends R> function, T t,
            boolean value) {
        return ObjBooleanFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjByteFunction<? super T, ? extends R> function, T t, byte value) {
        return ObjByteFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjCharFunction<? super T, ? extends R> function, T t, char value) {
        return ObjCharFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjDoubleFunction<? super T, ? extends R> function, T t, double value) {
        return ObjDoubleFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjFloatFunction<? super T, ? extends R> function, T t, float value) {
        return ObjFloatFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjIntFunction<? super T, ? extends R> function, T t, int value) {
        return ObjIntFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjLongFunction<? super T, ? extends R> function, T t, long value) {
        return ObjLongFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ObjShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, R> R call(@Nonnull final ObjShortFunction<? super T, ? extends R> function, T t, short value) {
        return ObjShortFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToByteObjByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToByteObjByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> byte call(@Nonnull final ToByteObjByteFunction<? super T> function, T t, byte value) {
        return ToByteObjByteFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToCharObjCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToCharObjCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> char call(@Nonnull final ToCharObjCharFunction<? super T> function, T t, char value) {
        return ToCharObjCharFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToDoubleObjDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToDoubleObjDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> double call(@Nonnull final ToDoubleObjDoubleFunction<? super T> function, T t, double value) {
        return ToDoubleObjDoubleFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToFloatObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToFloatObjFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> float call(@Nonnull final ToFloatObjFloatFunction<? super T> function, T t, float value) {
        return ToFloatObjFloatFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToIntObjIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToIntObjIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> int call(@Nonnull final ToIntObjIntFunction<? super T> function, T t, int value) {
        return ToIntObjIntFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToLongObjLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToLongObjLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> long call(@Nonnull final ToLongObjLongFunction<? super T> function, T t, long value) {
        return ToLongObjLongFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link ToShortObjShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToShortObjShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> short call(@Nonnull final ToShortObjShortFunction<? super T> function, T t, short value) {
        return ToShortObjShortFunction.call(function, t, value);
    }

    /**
     * Calls the given {@link BiObjBooleanFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjBooleanFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjBooleanFunction<? super T, ? super U, ? extends R> function, T t,
            U u, boolean value) {
        return BiObjBooleanFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjByteFunction<? super T, ? super U, ? extends R> function, T t,
            U u, byte value) {
        return BiObjByteFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjCharFunction<? super T, ? super U, ? extends R> function, T t,
            U u, char value) {
        return BiObjCharFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjDoubleFunction<? super T, ? super U, ? extends R> function, T t,
            U u, char value) {
        return BiObjDoubleFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjFloatFunction<? super T, ? super U, ? extends R> function, T t,
            U u, float value) {
        return BiObjFloatFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjIntFunction<? super T, ? super U, ? extends R> function, T t,
            U u, int value) {
        return BiObjIntFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjLongFunction<? super T, ? super U, ? extends R> function, T t,
            U u, long value) {
        return BiObjLongFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BiObjShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, R> R call(@Nonnull final BiObjShortFunction<? super T, ? super U, ? extends R> function, T t,
            U u, short value) {
        return BiObjShortFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToByteBiObjByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToByteBiObjByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> byte call(@Nonnull final ToByteBiObjByteFunction<? super T, ? super U> function, T t, U u,
            byte value) {
        return ToByteBiObjByteFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToCharBiObjCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToCharBiObjCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> char call(@Nonnull final ToCharBiObjCharFunction<? super T, ? super U> function, T t, U u,
            char value) {
        return ToCharBiObjCharFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToDoubleBiObjDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToDoubleBiObjDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> double call(@Nonnull final ToDoubleBiObjDoubleFunction<? super T, ? super U> function, T t,
            U u, double value) {
        return ToDoubleBiObjDoubleFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToFloatBiObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToFloatBiObjFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> float call(@Nonnull final ToFloatBiObjFloatFunction<? super T, ? super U> function, T t, U u,
            float value) {
        return ToFloatBiObjFloatFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToIntBiObjIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToIntBiObjIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> int call(@Nonnull final ToIntBiObjIntFunction<? super T, ? super U> function, T t, U u,
            int value) {
        return ToIntBiObjIntFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToLongBiObjLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToLongBiObjLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> long call(@Nonnull final ToLongBiObjLongFunction<? super T, ? super U> function, T t, U u,
            long value) {
        return ToLongBiObjLongFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link ToShortBiObjShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToShortBiObjShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> short call(@Nonnull final ToShortBiObjShortFunction<? super T, ? super U> function, T t, U u,
            short value) {
        return ToShortBiObjShortFunction.call(function, t, u, value);
    }

    /**
     * Calls the given {@link BooleanToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BooleanToByteFunction function, boolean value) {
        return BooleanToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BooleanToCharFunction function, boolean value) {
        return BooleanToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BooleanToDoubleFunction function, boolean value) {
        return BooleanToDoubleFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BooleanToFloatFunction function, boolean value) {
        return BooleanToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BooleanToIntFunction function, boolean value) {
        return BooleanToIntFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BooleanToLongFunction function, boolean value) {
        return BooleanToLongFunction.call(function, value);
    }

    /**
     * Calls the given {@link BooleanToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BooleanToShortFunction function, boolean value) {
        return BooleanToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final ByteToCharFunction function, byte value) {
        return ByteToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final ByteToDoubleFunction function, byte value) {
        return ByteToDoubleFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final ByteToFloatFunction function, byte value) {
        return ByteToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final ByteToIntFunction function, byte value) {
        return ByteToIntFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final ByteToLongFunction function, byte value) {
        return ByteToLongFunction.call(function, value);
    }

    /**
     * Calls the given {@link ByteToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final ByteToShortFunction function, byte value) {
        return ByteToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final CharToByteFunction function, char value) {
        return CharToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final CharToDoubleFunction function, char value) {
        return CharToDoubleFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final CharToFloatFunction function, char value) {
        return CharToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final CharToIntFunction function, char value) {
        return CharToIntFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final CharToLongFunction function, char value) {
        return CharToLongFunction.call(function, value);
    }

    /**
     * Calls the given {@link CharToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code CharToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final CharToShortFunction function, char value) {
        return CharToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link DoubleToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final DoubleToByteFunction function, double value) {
        return DoubleToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link DoubleToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final DoubleToCharFunction function, double value) {
        return DoubleToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link DoubleToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final DoubleToFloatFunction function, double value) {
        return DoubleToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link DoubleToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final DoubleToIntFunction function, double value) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value);
    }

    /**
     * Calls the given {@link DoubleToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final DoubleToLongFunction function, double value) {
        Objects.requireNonNull(function);
        return function.applyAsLong(value);
    }

    /**
     * Calls the given {@link DoubleToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final DoubleToShortFunction function, double value) {
        return DoubleToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link FloatToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final FloatToByteFunction function, float value) {
        return FloatToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link FloatToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final FloatToCharFunction function, float value) {
        return FloatToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link FloatToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final FloatToDoubleFunction function, float value) {
        return FloatToDoubleFunction.call(function, value);
    }

    /**
     * Calls the given {@link FloatToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final FloatToIntFunction function, float value) {
        return FloatToIntFunction.call(function, value);
    }

    /**
     * Calls the given {@link FloatToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final FloatToLongFunction function, float value) {
        return FloatToLongFunction.call(function, value);
    }

    /**
     * Calls the given {@link FloatToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final FloatToShortFunction function, float value) {
        return FloatToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link IntToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final IntToByteFunction function, int value) {
        return IntToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link IntToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final IntToCharFunction function, int value) {
        return IntToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link IntToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final IntToDoubleFunction function, int value) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(value);
    }

    /**
     * Calls the given {@link IntToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final IntToFloatFunction function, int value) {
        return IntToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link IntToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final IntToLongFunction function, int value) {
        Objects.requireNonNull(function);
        return function.applyAsLong(value);
    }

    /**
     * Calls the given {@link IntToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final IntToShortFunction function, int value) {
        return IntToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link LongToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final LongToByteFunction function, long value) {
        return LongToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link LongToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final LongToCharFunction function, long value) {
        return LongToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link LongToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final LongToDoubleFunction function, long value) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(value);
    }

    /**
     * Calls the given {@link LongToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final LongToFloatFunction function, long value) {
        return LongToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link LongToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final LongToIntFunction function, long value) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value);
    }

    /**
     * Calls the given {@link LongToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final LongToShortFunction function, long value) {
        return LongToShortFunction.call(function, value);
    }

    /**
     * Calls the given {@link ShortToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final ShortToByteFunction function, short value) {
        return ShortToByteFunction.call(function, value);
    }

    /**
     * Calls the given {@link ShortToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final ShortToCharFunction function, short value) {
        return ShortToCharFunction.call(function, value);
    }

    /**
     * Calls the given {@link ShortToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final ShortToDoubleFunction function, short value) {
        return ShortToDoubleFunction.call(function, value);
    }

    /**
     * Calls the given {@link ShortToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final ShortToFloatFunction function, short value) {
        return ShortToFloatFunction.call(function, value);
    }

    /**
     * Calls the given {@link ShortToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final ShortToIntFunction function, short value) {
        return ShortToIntFunction.call(function, value);
    }

    /**
     * Calls the given {@link ShortToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final ShortToLongFunction function, short value) {
        return ShortToLongFunction.call(function, value);
    }

    /**
     * Calls the given {@link BiBooleanToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiBooleanToByteFunction function, boolean value1, boolean value2) {
        return BiBooleanToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiBooleanToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiBooleanToCharFunction function, boolean value1, boolean value2) {
        return BiBooleanToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiBooleanToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiBooleanToDoubleFunction function, boolean value1, boolean value2) {
        return BiBooleanToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiBooleanToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiBooleanToFloatFunction function, boolean value1, boolean value2) {
        return BiBooleanToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiBooleanToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiBooleanToIntFunction function, boolean value1, boolean value2) {
        return BiBooleanToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiBooleanToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiBooleanToLongFunction function, boolean value1, boolean value2) {
        return BiBooleanToLongFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiBooleanToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BiBooleanToShortFunction function, boolean value1, boolean value2) {
        return BiBooleanToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiByteToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiByteToCharFunction function, byte value1, byte value2) {
        return BiByteToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiByteToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiByteToDoubleFunction function, byte value1, byte value2) {
        return BiByteToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiByteToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiByteToFloatFunction function, byte value1, byte value2) {
        return BiByteToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiByteToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiByteToIntFunction function, byte value1, byte value2) {
        return BiByteToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiByteToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiByteToLongFunction function, byte value1, byte value2) {
        return BiByteToLongFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiByteToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BiByteToShortFunction function, byte value1, byte value2) {
        return BiByteToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiCharToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiCharToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiCharToByteFunction function, char value1, char value2) {
        return BiCharToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiCharToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiCharToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiCharToDoubleFunction function, char value1, char value2) {
        return BiCharToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiCharToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiCharToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiCharToFloatFunction function, char value1, char value2) {
        return BiCharToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiCharToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiCharToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiCharToIntFunction function, char value1, char value2) {
        return BiCharToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiCharToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiCharToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiCharToLongFunction function, char value1, char value2) {
        return BiCharToLongFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiCharToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiCharToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BiCharToShortFunction function, char value1, char value2) {
        return BiCharToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiDoubleToByteFunction function, double value1, double value2) {
        return BiDoubleToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiDoubleToCharFunction function, double value1, double value2) {
        return BiDoubleToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiDoubleToFloatFunction function, double value1, double value2) {
        return BiDoubleToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiDoubleToIntFunction function, double value1, double value2) {
        return BiDoubleToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiDoubleToLongFunction function, double value1, double value2) {
        return BiDoubleToLongFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BiDoubleToShortFunction function, double value1, double value2) {
        return BiDoubleToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiFloatToByteFunction function, float value1, float value2) {
        return BiFloatToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiFloatToCharFunction function, float value1, float value2) {
        return BiFloatToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiFloatToDoubleFunction function, float value1, float value2) {
        return BiFloatToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiFloatToIntFunction function, float value1, float value2) {
        return BiFloatToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiFloatToLongFunction function, float value1, float value2) {
        return BiFloatToLongFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BiFloatToShortFunction function, float value1, float value2) {
        return BiFloatToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiIntToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiIntToByteFunction function, int value1, int value2) {
        return BiIntToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiIntToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiIntToCharFunction function, int value1, int value2) {
        return BiIntToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiIntToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiIntToDoubleFunction function, int value1, int value2) {
        return BiIntToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiIntToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiIntToFloatFunction function, int value1, int value2) {
        return BiIntToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiIntToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiIntToLongFunction function, int value1, int value2) {
        return BiIntToLongFunction.call(function, value1, value2);
    }

    /**
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type. Calls the given
     * {@link BiIntToShortFunction} with the given arguments and returns its result.
     */
    public static short call(@Nonnull final BiIntToShortFunction function, int value1, int value2) {
        return BiIntToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiLongToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiLongToByteFunction function, long value1, long value2) {
        return BiLongToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiLongToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiLongToCharFunction function, long value1, long value2) {
        return BiLongToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiLongToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiLongToDoubleFunction function, long value1, long value2) {
        return BiLongToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiLongToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiLongToFloatFunction function, long value1, long value2) {
        return BiLongToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiLongToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiLongToIntFunction function, long value1, long value2) {
        return BiLongToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiLongToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final BiLongToShortFunction function, long value1, long value2) {
        return BiLongToShortFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiShortToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final BiShortToByteFunction function, short value1, short value2) {
        return BiShortToByteFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiShortToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final BiShortToCharFunction function, short value1, short value2) {
        return BiShortToCharFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiShortToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final BiShortToDoubleFunction function, short value1, short value2) {
        return BiShortToDoubleFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiShortToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final BiShortToFloatFunction function, short value1, short value2) {
        return BiShortToFloatFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiShortToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final BiShortToIntFunction function, short value1, short value2) {
        return BiShortToIntFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link BiShortToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final BiShortToLongFunction function, short value1, short value2) {
        return BiShortToLongFunction.call(function, value1, value2);
    }

    /**
     * Calls the given {@link TriBooleanToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriBooleanToByteFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriBooleanToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriBooleanToCharFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriBooleanToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriBooleanToDoubleFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriBooleanToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriBooleanToFloatFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriBooleanToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriBooleanToIntFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriBooleanToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriBooleanToLongFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriBooleanToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final TriBooleanToShortFunction function, boolean value1, boolean value2,
            boolean value3) {
        return TriBooleanToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriByteToCharFunction function, byte value1, byte value2, byte value3) {
        return TriByteToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriByteToDoubleFunction function, byte value1, byte value2, byte value3) {
        return TriByteToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriByteToFloatFunction function, byte value1, byte value2, byte value3) {
        return TriByteToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriByteToIntFunction function, byte value1, byte value2, byte value3) {
        return TriByteToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriByteToLongFunction function, byte value1, byte value2, byte value3) {
        return TriByteToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final TriByteToShortFunction function, byte value1, byte value2, byte value3) {
        return TriByteToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriCharToByteFunction function, char value1, char value2, char value3) {
        return TriCharToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriCharToDoubleFunction function, char value1, char value2, char value3) {
        return TriCharToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriCharToFloatFunction function, char value1, char value2, char value3) {
        return TriCharToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriCharToIntFunction function, char value1, char value2, char value3) {
        return TriCharToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriCharToLongFunction function, char value1, char value2, char value3) {
        return TriCharToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final TriCharToShortFunction function, char value1, char value2, char value3) {
        return TriCharToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriDoubleToByteFunction function, double value1, double value2,
            double value3) {
        return TriDoubleToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriDoubleToCharFunction function, double value1, double value2,
            double value3) {
        return TriDoubleToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriDoubleToFloatFunction function, double value1, double value2,
            double value3) {
        return TriDoubleToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriDoubleToIntFunction function, double value1, double value2,
            double value3) {
        return TriDoubleToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriDoubleToLongFunction function, double value1, double value2,
            double value3) {
        return TriDoubleToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final TriDoubleToShortFunction function, double value1, double value2,
            double value3) {
        return TriDoubleToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriFloatToByteFunction function, float value1, float value2, float value3) {
        return TriFloatToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriFloatToCharFunction function, float value1, float value2, float value3) {
        return TriFloatToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriFloatToDoubleFunction function, float value1, float value2,
            float value3) {
        return TriFloatToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriFloatToIntFunction function, float value1, float value2, float value3) {
        return TriFloatToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriFloatToLongFunction function, float value1, float value2, float value3) {
        return TriFloatToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.}.
     */
    public static short call(@Nonnull final TriFloatToShortFunction function, float value1, float value2,
            float value3) {
        return TriFloatToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriIntToByteFunction function, int value1, int value2, int value3) {
        return TriIntToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriIntToCharFunction function, int value1, int value2, int value3) {
        return TriIntToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriIntToDoubleFunction function, int value1, int value2, int value3) {
        return TriIntToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriIntToFloatFunction function, int value1, int value2, int value3) {
        return TriIntToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriIntToLongFunction function, int value1, int value2, int value3) {
        return TriIntToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final TriIntToShortFunction function, int value1, int value2, int value3) {
        return TriIntToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriLongToByteFunction function, long value1, long value2, long value3) {
        return TriLongToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriLongToCharFunction function, long value1, long value2, long value3) {
        return TriLongToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriLongToDoubleFunction function, long value1, long value2, long value3) {
        return TriLongToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriLongToFloatFunction function, long value1, long value2, long value3) {
        return TriLongToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriLongToIntFunction function, long value1, long value2, long value3) {
        return TriLongToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final TriLongToShortFunction function, long value1, long value2, long value3) {
        return TriLongToShortFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriShortToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final TriShortToByteFunction function, short value1, short value2, short value3) {
        return TriShortToByteFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriShortToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final TriShortToCharFunction function, short value1, short value2, short value3) {
        return TriShortToCharFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriShortToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static double call(@Nonnull final TriShortToDoubleFunction function, short value1, short value2,
            short value3) {
        return TriShortToDoubleFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriShortToFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final TriShortToFloatFunction function, short value1, short value2,
            short value3) {
        return TriShortToFloatFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriShortToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static int call(@Nonnull final TriShortToIntFunction function, short value1, short value2, short value3) {
        return TriShortToIntFunction.call(function, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriShortToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static long call(@Nonnull final TriShortToLongFunction function, short value1, short value2, short value3) {
        return TriShortToLongFunction.call(function, value1, value2, value3);
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> The type of the argument and output objects to the function
     * @return A function that always returns its input argument.
     */
    public static <T> Function<T, T> identity() {
        return Function.identity();
    }

    /**
     * Returns a {@link Function} which performs a {@link Map} lookup. The returned function throws an {@link
     * IllegalArgumentException} if given a key that does not exist in the map.
     *
     * @param <K> The type of the keys maintained by the given map and of the argument to the function
     * @param <V> The type of mapped values and of return value from the function
     * @param map Source map that determines the function's behavior
     * @return A {@code Function} which performs a {@code Map lookup}.
     * @throws NullPointerException If the given map is {@code null}
     * @throws IllegalArgumentException If given a key that does not exist in the map
     * @see #forMap(Map, Object)
     */
    @Nonnull
    public static <K, V> Function<K, V> forMap(@Nonnull final Map<K, V> map) {
        Objects.requireNonNull(map);
        return key -> {
            if (map.containsKey(key)) {
                return map.get(key);
            } else {
                throw new IllegalArgumentException("Key '" + key + "' not present in map");
            }
        };
    }

    /**
     * Returns a {@link Function} which performs a {@link Map} lookup. The returned function throws an {@link
     * IllegalArgumentException} if given a key that does not exist in the map.
     *
     * @param <K> The type of the keys maintained by the given map and of the argument to the function
     * @param <V> The type of mapped values and of return value from the function
     * @param map Source map that determines the function's behavior
     * @param exceptionMessage A {@link String} that represents a message for the thrown exception, in case the map's
     * key does not exist
     * @return A {@code Function} which performs a {@code Map lookup}.
     * @throws NullPointerException If the given map is {@code null}
     * @throws IllegalArgumentException If given a key that does not exist in the map
     * @see #forMap(Map, Object)
     */
    @Nonnull
    public static <K, V> Function<K, V> forMap(@Nonnull final Map<K, V> map, @Nonnull final String exceptionMessage) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(exceptionMessage);
        return key -> {
            if (map.containsKey(key)) {
                return map.get(key);
            } else {
                throw new IllegalArgumentException(exceptionMessage);
            }
        };
    }

    /**
     * Returns a {@link Function} which performs a {@link Map} lookup. The returned function throws an {@link
     * IllegalArgumentException} if given a key that does not exist in the map.
     *
     * @param <K> The type of the keys maintained by the given map and of the argument to the function
     * @param <V> The type of mapped values and of return value from the function
     * @param map Source map that determines the function's behavior
     * @param supplier A {@link Supplier} that provides a message for the thrown exception, in case the map's key does
     * not exist
     * @return A {@code Function} which performs a {@code Map lookup}.
     * @throws NullPointerException If the given map is {@code null}
     * @throws IllegalArgumentException If given a key that does not exist in the map
     * @see #forMap(Map, Object)
     */
    @Nonnull
    public static <K, V> Function<K, V> forMap(@Nonnull final Map<K, V> map, @Nonnull final Supplier<String> supplier) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(supplier);
        return key -> {
            if (map.containsKey(key)) {
                return map.get(key);
            } else {
                throw new IllegalArgumentException(supplier.get());
            }
        };
    }

    /**
     * Returns a {@link Function} which performs a {@link Map} lookup with a default value. The function created by this
     * method returns {@code defaultValue} for all inputs that do not belong to the map's key set.
     *
     * @param <K> The type of the keys maintained by the given map and of the argument to the function
     * @param <V> The type of mapped values and of return value from the function
     * @param map Source map that determines the function's behavior
     * @param defaultValue The value to return for inputs that aren't map keys
     * @return A {@code Function} which performs a {@code Map} lookup with a default value.
     * @throws NullPointerException If the given map is {@code null}
     * @implSpec This implementation returns a function that returns {@code map.get(k)} when {@code k} is a key, or
     * {@code defaultValue} otherwise. Thereby the following operation {@code map.contains(k) ? map.get(k) :
     * defaultValue} is used.
     * @see #forMap(Map)
     * @see #forMap(Map, String)
     * @see #forMap(Map, Supplier)
     */
    @Nonnull
    public static <K, V> Function<K, V> forMap(@Nonnull final Map<K, ? extends V> map, @Nullable final V defaultValue) {
        Objects.requireNonNull(map);
        return k -> map.containsKey(k) ? map.get(k) : defaultValue;
    }

    /**
     * Creates a {@link Function} that always accepts a single input argument given to {@link Consumer#accept(Object)}
     * on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code Function} that always accepts a single input argument given to {@code Consumer.accept(Object)}
     * on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> Function<T, Void> forConsumer(@Nonnull final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return t -> {
            consumer.accept(t);
            return null;
        };
    }

    /**
     * Creates a {@link BiFunction} that always accepts two input arguments given to {@link BiConsumer#accept(Object,
     * Object)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code Function} that always accepts a two input arguments given to {@code BiConsumer.accept(Object,
     * Object)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiFunction<T, U, Void> forConsumer(@Nonnull final BiConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> {
            consumer.accept(t, u);
            return null;
        };
    }

    /**
     * Creates a {@link TriFunction} that always accepts three input arguments given to {@link
     * TriConsumer#accept(Object, Object, Object)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param <V> The type of the third argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code TriFunction} that always accepts three input arguments given to {@code
     * TriConsumer.accept(Object, Object, Object)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U, V> TriFunction<T, U, V, Void> forConsumer(
            @Nonnull final TriConsumer<? super T, ? super U, ? super V> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> {
            consumer.accept(t, u, v);
            return null;
        };
    }

    /**
     * Creates a {@link BooleanFunction} that always accepts a single input argument given to {@link
     * BooleanConsumer#accept(boolean)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BooleanFunction} that always accepts a single input argument given to {@code
     * BooleanConsumer.accept(boolean)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static BooleanFunction<Void> forConsumer(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link ByteFunction} that always accepts a single input argument given to {@link
     * ByteConsumer#accept(byte)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ByteFunction} that always accepts a single input argument given to {@code
     * ByteConsumer.accept(byte)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static ByteFunction<Void> forConsumer(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link CharFunction} that always accepts a single input argument given to {@link
     * CharConsumer#accept(char)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code CharFunction} that always accepts a single input argument given to {@code
     * CharConsumer.accept(char)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static CharFunction<Void> forConsumer(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link DoubleFunction} that always accepts a single input argument given to {@link
     * DoubleConsumer#accept(double)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code DoubleFunction} that always accepts a single input argument given to {@code
     * DoubleConsumer.accept(double)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static DoubleFunction<Void> forConsumer(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link FloatFunction} that always accepts a single input argument given to {@link
     * FloatConsumer#accept(float)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code FloatFunction} that always accepts a single input argument given to {@code
     * FloatConsumer.accept(float)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static FloatFunction<Void> forConsumer(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link IntFunction} that always accepts a single input argument given to {@link
     * IntConsumer#accept(int)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code IntFunction} that always accepts a single input argument given to {@code
     * IntConsumer.accept(int)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static IntFunction<Void> forConsumer(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link LongFunction} that always accepts a single input argument given to {@link
     * LongConsumer#accept(long)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code LongFunction} that always accepts a single input argument given to {@code
     * LongConsumer.accept(long)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static LongFunction<Void> forConsumer(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link ShortFunction} that always accepts a single input argument given to {@link
     * ShortConsumer#accept(short)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ShortFunction} that always accepts a single input argument given to {@code
     * ShortConsumer.accept(short)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static ShortFunction<Void> forConsumer(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> {
            consumer.accept(value);
            return null;
        };
    }

    /**
     * Creates a {@link BooleanBiFunction} that always accepts two input arguments given to {@link
     * BooleanBiConsumer#accept(boolean, boolean)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BooleanBiFunction} that always accepts two input arguments given to {@code
     * BooleanBiConsumer.accept(boolean, boolean)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static BooleanBiFunction<Void> forConsumer(@Nonnull final BooleanBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link ByteBiFunction} that always accepts two input arguments given to {@link
     * ByteBiConsumer#accept(byte, byte)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ByteBiFunction} that always accepts two input arguments given to {@code
     * ByteBiConsumer.accept(byte, byte)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static ByteBiFunction<Void> forConsumer(@Nonnull final ByteBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link CharBiFunction} that always accepts two input arguments given to {@link
     * CharBiConsumer#accept(char, char)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code CharBiFunction} that always accepts two input arguments given to {@code
     * CharBiConsumer.accept(char, char)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static CharBiFunction<Void> forConsumer(@Nonnull final CharBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link DoubleBiFunction} that always accepts two input arguments given to {@link
     * DoubleBiConsumer#accept(double, double)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code DoubleBiFunction} that always accepts two input arguments given to {@code
     * DoubleBiConsumer.accept(double, double)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static DoubleBiFunction<Void> forConsumer(@Nonnull final DoubleBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link FloatBiFunction} that always accepts two input arguments given to {@link
     * FloatBiConsumer#accept(float, float)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code FloatBiFunction} that always accepts two input arguments given to {@code
     * FloatBiConsumer.accept(float, float)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static FloatBiFunction<Void> forConsumer(@Nonnull final FloatBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link IntBiFunction} that always accepts two input arguments given to {@link IntBiConsumer#accept(int,
     * int)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code IntBiFunction} that always accepts two input arguments given to {@code IntBiConsumer.accept(int,
     * int)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static IntBiFunction<Void> forConsumer(@Nonnull final IntBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link LongBiFunction} that always accepts two input arguments given to {@link
     * LongBiConsumer#accept(long, long)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code LongBiFunction} that always accepts two input arguments given to {@code
     * LongBiConsumer.accept(long, long)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static LongBiFunction<Void> forConsumer(@Nonnull final LongBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link ShortBiFunction} that always accepts two input arguments given to {@link
     * ShortBiConsumer#accept(short, short)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ShortBiFunction} that always accepts two input arguments given to {@code
     * ShortBiConsumer.accept(short, short)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static ShortBiFunction<Void> forConsumer(@Nonnull final ShortBiConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> {
            consumer.accept(value1, value2);
            return null;
        };
    }

    /**
     * Creates a {@link BooleanTriFunction} that always accepts three input arguments given to {@link
     * BooleanTriConsumer#accept(boolean, boolean, boolean)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BooleanTriFunction} that always accepts three input arguments given to {@code
     * BooleanTriConsumer.accept(boolean, boolean, boolean)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static BooleanTriFunction<Void> forConsumer(@Nonnull final BooleanTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link ByteTriFunction} that always accepts three input arguments given to {@link
     * ByteTriConsumer#accept(byte, byte, byte)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ByteTriFunction} that always accepts three input arguments given to {@code
     * ByteTriConsumer.accept(byte, byte, byte)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static ByteTriFunction<Void> forConsumer(@Nonnull final ByteTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link CharTriFunction} that always accepts three input arguments given to {@link
     * CharTriConsumer#accept(char, char, char)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code CharTriFunction} that always accepts three input arguments given to {@code
     * CharTriConsumer.accept(char, char, char)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static CharTriFunction<Void> forConsumer(@Nonnull final CharTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link DoubleTriFunction} that always accepts three input arguments given to {@link
     * DoubleTriConsumer#accept(double, double, double)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code DoubleTriFunction} that always accepts three input arguments given to {@code
     * DoubleTriConsumer.accept(double, double, double)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static DoubleTriFunction<Void> forConsumer(@Nonnull final DoubleTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link FloatTriFunction} that always accepts three input arguments given to {@link
     * FloatTriConsumer#accept(float, float, float)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code FloatTriFunction} that always accepts three input arguments given to {@code
     * FloatTriConsumer.accept(float, float, float)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static FloatTriFunction<Void> forConsumer(@Nonnull final FloatTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link IntTriFunction} that always accepts three input arguments given to {@link
     * IntTriConsumer#accept(int, int, int)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code IntTriFunction} that always accepts three input arguments given to {@code
     * IntTriConsumer.accept(int, int, int)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static IntTriFunction<Void> forConsumer(@Nonnull final IntTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link LongTriFunction} that always accepts three input arguments given to {@link
     * LongTriConsumer#accept(long, long, long)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code LongTriFunction} that always accepts three input arguments given to {@code
     * LongTriConsumer.accept(long, long, long)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static LongTriFunction<Void> forConsumer(@Nonnull final LongTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link ShortTriFunction} that always accepts three input arguments given to {@link
     * ShortTriConsumer#accept(short, short, short)} on {@code consumer} and returns no value.
     *
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ShortTriFunction} that always accepts three input arguments given to {@code
     * ShortTriConsumer.accept(short, short, short)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static ShortTriFunction<Void> forConsumer(@Nonnull final ShortTriConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> {
            consumer.accept(value1, value2, value3);
            return null;
        };
    }

    /**
     * Creates a {@link ObjBooleanFunction} that always accepts two input arguments given to {@link
     * ObjBooleanConsumer#accept(Object, boolean)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjBooleanFunction} that always accepts two input arguments given to {@code
     * ObjBooleanConsumer.accept(Object, boolean)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjBooleanFunction<T, Void> forConsumer(@Nonnull final ObjBooleanConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjByteFunction} that always accepts two input arguments given to {@link
     * ObjByteConsumer#accept(Object, byte)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjByteFunction} that always accepts two input arguments given to {@code
     * ObjByteConsumer.accept(Object, byte)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjByteFunction<T, Void> forConsumer(@Nonnull final ObjByteConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjCharFunction} that always accepts two input arguments given to {@link
     * ObjCharConsumer#accept(Object, char)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjCharFunction} that always accepts two input arguments given to {@code
     * ObjCharConsumer.accept(Object, char)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjCharFunction<T, Void> forConsumer(@Nonnull final ObjCharConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjDoubleFunction} that always accepts two input arguments given to {@link
     * ObjDoubleConsumer#accept(Object, double)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjDoubleFunction} that always accepts two input arguments given to {@code
     * ObjDoubleConsumer.accept(Object, double)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjDoubleFunction<T, Void> forConsumer(@Nonnull final ObjDoubleConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjFloatFunction} that always accepts two input arguments given to {@link
     * ObjFloatConsumer#accept(Object, float)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjFloatFunction} that always accepts two input arguments given to {@code
     * ObjFloatConsumer.accept(Object, float)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjFloatFunction<T, Void> forConsumer(@Nonnull final ObjFloatConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjIntFunction} that always accepts two input arguments given to {@link
     * ObjIntConsumer#accept(Object, int)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjIntFunction} that always accepts two input arguments given to {@code
     * ObjIntConsumer.accept(Object, int)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjIntFunction<T, Void> forConsumer(@Nonnull final ObjIntConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjLongFunction} that always accepts two input arguments given to {@link
     * ObjLongConsumer#accept(Object, long)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjLongFunction} that always accepts two input arguments given to {@code
     * ObjLongConsumer.accept(Object, long)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjLongFunction<T, Void> forConsumer(@Nonnull final ObjLongConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link ObjShortFunction} that always accepts two input arguments given to {@link
     * ObjShortConsumer#accept(Object, short)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code ObjShortFunction} that always accepts two input arguments given to {@code
     * ObjShortConsumer.accept(Object, short)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T> ObjShortFunction<T, Void> forConsumer(@Nonnull final ObjShortConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> {
            consumer.accept(t, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjBooleanFunction} that always accepts three input arguments given to {@link
     * BiObjBooleanConsumer#accept(Object, Object, boolean)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjBooleanFunction} that always accepts three input arguments given to {@code
     * BiObjBooleanConsumer.accept(Object, Object, boolean)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjBooleanFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjBooleanConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjByteFunction} that always accepts three input arguments given to {@link
     * BiObjByteConsumer#accept(Object, Object, byte)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjByteFunction} that always accepts three input arguments given to {@code
     * BiObjByteConsumer.accept(Object, Object, byte)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjByteFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjByteConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjCharFunction} that always accepts three input arguments given to {@link
     * BiObjCharConsumer#accept(Object, Object, char)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjCharFunction} that always accepts three input arguments given to {@code
     * BiObjCharConsumer.accept(Object, Object, char)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjCharFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjCharConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjDoubleFunction} that always accepts three input arguments given to {@link
     * BiObjDoubleConsumer#accept(Object, Object, double)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjDoubleFunction} that always accepts three input arguments given to {@code
     * BiObjDoubleConsumer.accept(Object, Object, double)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjDoubleFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjDoubleConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjFloatFunction} that always accepts three input arguments given to {@link
     * BiObjFloatConsumer#accept(Object, Object, float)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjFloatFunction} that always accepts three input arguments given to {@code
     * BiObjFloatConsumer.accept(Object, Object, float)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjFloatFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjFloatConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjIntFunction} that always accepts three input arguments given to {@link
     * BiObjIntConsumer#accept(Object, Object, int)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjIntFunction} that always accepts three input arguments given to {@code
     * BiObjIntConsumer.accept(Object, Object, int)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjIntFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjIntConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjLongFunction} that always accepts three input arguments given to {@link
     * BiObjLongConsumer#accept(Object, Object, long)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjLongFunction} that always accepts three input arguments given to {@code
     * BiObjLongConsumer.accept(Object, Object, long)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjLongFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjLongConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link BiObjShortFunction} that always accepts three input arguments given to {@link
     * BiObjShortConsumer#accept(Object, Object, short)} on {@code consumer} and returns no value.
     *
     * @param <T> The type of the first argument to the consumer and the function
     * @param <U> The type of the second argument to the consumer and the function
     * @param consumer The consumer to be transformed to a function
     * @return A {@code BiObjShortFunction} that always accepts three input arguments given to {@code
     * BiObjShortConsumer.accept(Object, Object, short)} on {@code consumer} and returns no value.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The default implementation always returns a function which firstly executes the given consumer and then
     * returns {@code null}.
     */
    @Nonnull
    public static <T, U> BiObjShortFunction<T, U, Void> forConsumer(
            @Nonnull final BiObjShortConsumer<? super T, ? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> {
            consumer.accept(t, u, value);
            return null;
        };
    }

    /**
     * Creates a {@link Function} that returns the same boolean output as the given {@link Predicate} for all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code Function} that returns the same boolean output as the given {@code Predicate} for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> Function<T, Boolean> forPredicate(@Nonnull final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiFunction} that returns the same boolean output as the given {@link BiPredicate} for all
     * inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiFunction} that returns the same boolean output as the given {@code BiPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiPredicate<? super T, ? super U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link TriFunction} that returns the same boolean output as the given {@link TriPredicate} for all
     * inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param <V> The type of the third argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code TriFunction} that returns the same boolean output as the given {@code TriPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U, V> TriFunction<T, U, V, Boolean> forPredicate(
            @Nonnull final TriPredicate<? super T, ? super U, ? super V> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ByteFunction} that returns the same boolean output as the given {@link BytePredicate} for all
     * inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ByteFunction} that returns the same boolean output as the given {@code BytePredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ByteFunction<Boolean> forPredicate(@Nonnull final BytePredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link CharFunction} that returns the same boolean output as the given {@link CharPredicate} for all
     * inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code CharFunction} that returns the same boolean output as the given {@code CharPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static CharFunction<Boolean> forPredicate(@Nonnull final CharPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link DoubleFunction} that returns the same boolean output as the given {@link DoublePredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code DoubleFunction} that returns the same boolean output as the given {@code DoublePredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static DoubleFunction<Boolean> forPredicate(@Nonnull final DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link FloatFunction} that returns the same boolean output as the given {@link FloatPredicate} for all
     * inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code FloatFunction} that returns the same boolean output as the given {@code FloatPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static FloatFunction<Boolean> forPredicate(@Nonnull final FloatPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link IntFunction} that returns the same boolean output as the given {@link IntPredicate} for all
     * inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code IntFunction} that returns the same boolean output as the given {@code IntPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static IntFunction<Boolean> forPredicate(@Nonnull final IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link LongFunction} that returns the same boolean output as the given {@link LongPredicate} for all
     * inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code LongFunction} that returns the same boolean output as the given {@code LongPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static LongFunction<Boolean> forPredicate(@Nonnull final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ShortFunction} that returns the same boolean output as the given {@link ShortPredicate} for all
     * inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ShortFunction} that returns the same boolean output as the given {@code ShortPredicate} for all
     * inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ShortFunction<Boolean> forPredicate(@Nonnull final ShortPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ByteBiFunction} that returns the same boolean output as the given {@link ByteBiPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ByteBiFunction} that returns the same boolean output as the given {@code ByteBiPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ByteBiFunction<Boolean> forPredicate(@Nonnull final ByteBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link CharBiFunction} that returns the same boolean output as the given {@link CharBiPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code CharBiFunction} that returns the same boolean output as the given {@code CharBiPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static CharBiFunction<Boolean> forPredicate(@Nonnull final CharBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link DoubleBiFunction} that returns the same boolean output as the given {@link DoubleBiPredicate}
     * for all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code DoubleBiFunction} that returns the same boolean output as the given {@code DoubleBiPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static DoubleBiFunction<Boolean> forPredicate(@Nonnull final DoubleBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link FloatBiFunction} that returns the same boolean output as the given {@link FloatBiPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code FloatBiFunction} that returns the same boolean output as the given {@code FloatBiPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static FloatBiFunction<Boolean> forPredicate(@Nonnull final FloatBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link IntBiFunction} that returns the same boolean output as the given {@link FloatBiPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code IntBiFunction} that returns the same boolean output as the given {@code FloatBiPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static IntBiFunction<Boolean> forPredicate(@Nonnull final IntBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link LongBiFunction} that returns the same boolean output as the given {@link LongBiPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code LongBiFunction} that returns the same boolean output as the given {@code LongBiPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static LongBiFunction<Boolean> forPredicate(@Nonnull final LongBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ShortBiFunction} that returns the same boolean output as the given {@link ShortBiPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ShortBiFunction} that returns the same boolean output as the given {@code ShortBiPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ShortBiFunction<Boolean> forPredicate(@Nonnull final ShortBiPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ByteTriFunction} that returns the same boolean output as the given {@link ByteTriPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ByteTriFunction} that returns the same boolean output as the given {@code ByteTriPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ByteTriFunction<Boolean> forPredicate(@Nonnull final ByteTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link CharTriFunction} that returns the same boolean output as the given {@link CharTriPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code CharTriFunction} that returns the same boolean output as the given {@code CharTriPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static CharTriFunction<Boolean> forPredicate(@Nonnull final CharTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link DoubleTriFunction} that returns the same boolean output as the given {@link DoubleTriPredicate}
     * for all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code DoubleTriFunction} that returns the same boolean output as the given {@code DoubleTriPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static DoubleTriFunction<Boolean> forPredicate(@Nonnull final DoubleTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link FloatTriFunction} that returns the same boolean output as the given {@link FloatTriPredicate}
     * for all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code FloatTriFunction} that returns the same boolean output as the given {@code FloatTriPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static FloatTriFunction<Boolean> forPredicate(@Nonnull final FloatTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link IntTriFunction} that returns the same boolean output as the given {@link IntTriPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code IntTriFunction} that returns the same boolean output as the given {@code IntTriPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static IntTriFunction<Boolean> forPredicate(@Nonnull final IntTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link LongTriFunction} that returns the same boolean output as the given {@link LongTriPredicate} for
     * all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code LongTriFunction} that returns the same boolean output as the given {@code LongTriPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static LongTriFunction<Boolean> forPredicate(@Nonnull final LongTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ShortTriFunction} that returns the same boolean output as the given {@link ShortTriPredicate}
     * for all inputs.
     *
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ShortTriFunction} that returns the same boolean output as the given {@code ShortTriPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ShortTriFunction<Boolean> forPredicate(@Nonnull final ShortTriPredicate predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjByteFunction} that returns the same boolean output as the given {@link ObjBytePredicate} for
     * all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjByteFunction} that returns the same boolean output as the given {@code ObjBytePredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjByteFunction<T, Boolean> forPredicate(@Nonnull final ObjBytePredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjCharFunction} that returns the same boolean output as the given {@link ObjCharPredicate} for
     * all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjCharFunction} that returns the same boolean output as the given {@code ObjCharPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjCharFunction<T, Boolean> forPredicate(@Nonnull final ObjCharPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjDoubleFunction} that returns the same boolean output as the given {@link ObjDoublePredicate}
     * for all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjDoubleFunction} that returns the same boolean output as the given {@code ObjDoublePredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjDoubleFunction<T, Boolean> forPredicate(@Nonnull final ObjDoublePredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjFloatFunction} that returns the same boolean output as the given {@link ObjFloatPredicate}
     * for all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjFloatFunction} that returns the same boolean output as the given {@code ObjFloatPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjFloatFunction<T, Boolean> forPredicate(@Nonnull final ObjFloatPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjIntFunction} that returns the same boolean output as the given {@link ObjIntPredicate} for
     * all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjIntFunction} that returns the same boolean output as the given {@code ObjIntPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjIntFunction<T, Boolean> forPredicate(@Nonnull final ObjIntPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjLongFunction} that returns the same boolean output as the given {@link ObjLongPredicate} for
     * all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjLongFunction} that returns the same boolean output as the given {@code ObjLongPredicate} for
     * all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjLongFunction<T, Boolean> forPredicate(@Nonnull final ObjLongPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link ObjShortFunction} that returns the same boolean output as the given {@link ObjShortPredicate}
     * for all inputs.
     *
     * @param <T> The type of the input to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code ObjShortFunction} that returns the same boolean output as the given {@code ObjShortPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> ObjShortFunction<T, Boolean> forPredicate(@Nonnull final ObjShortPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjByteFunction} that returns the same boolean output as the given {@link BiObjBytePredicate}
     * for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjByteFunction} that returns the same boolean output as the given {@code BiObjBytePredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjByteFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjBytePredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjCharFunction} that returns the same boolean output as the given {@link BiObjCharPredicate}
     * for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjCharFunction} that returns the same boolean output as the given {@code BiObjCharPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjCharFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjCharPredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjDoubleFunction} that returns the same boolean output as the given {@link
     * BiObjDoublePredicate} for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjDoubleFunction} that returns the same boolean output as the given {@code
     * BiObjDoublePredicate} for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjDoubleFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjDoublePredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjFloatFunction} that returns the same boolean output as the given {@link
     * BiObjFloatPredicate} for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjFloatFunction} that returns the same boolean output as the given {@code
     * BiObjFloatPredicate} for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjFloatFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjFloatPredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjIntFunction} that returns the same boolean output as the given {@link BiObjIntPredicate}
     * for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjIntFunction} that returns the same boolean output as the given {@code BiObjIntPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjIntFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjIntPredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjLongFunction} that returns the same boolean output as the given {@link BiObjLongPredicate}
     * for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjLongFunction} that returns the same boolean output as the given {@code BiObjLongPredicate}
     * for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjLongFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjLongPredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link BiObjShortFunction} that returns the same boolean output as the given {@link
     * BiObjShortPredicate} for all inputs.
     *
     * @param <T> The type of the first argument to the predicate and the function
     * @param <U> The type of the second argument to the predicate and the function
     * @param predicate The predicate to be transformed to a function
     * @return A {@code BiObjShortFunction} that returns the same boolean output as the given {@code
     * BiObjShortPredicate} for all inputs.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T, U> BiObjShortFunction<T, U, Boolean> forPredicate(
            @Nonnull final BiObjShortPredicate<T, U> predicate) {
        Objects.requireNonNull(predicate);
        return predicate::test;
    }

    /**
     * Creates a {@link Function} that always returns the result of invoking {@link Supplier#get()} on {@code supplier},
     * regardless of its input.
     *
     * @param <T> The type of results supplied by the given supplier and the functions return value
     * @param supplier The supplier to be invoked
     * @return A {@code Function} that always returns the result of invoking {@code Supplier.get()} on {@code supplier}
     * regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> Function<Object, T> forSupplier(@Nonnull final Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.get();
    }

    /**
     * Creates a {@link ToByteFunction} that always returns the result of invoking {@link ByteSupplier#getAsByte()} on
     * {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToByteFunction} that always returns the result of invoking {@code ByteSupplier.getAsByte()} on
     * {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToByteFunction<Object> forSupplier(@Nonnull final ByteSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsByte();
    }

    /**
     * Creates a {@link ToCharFunction} that always returns the result of invoking {@link CharSupplier#getAsChar()} on
     * {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToCharFunction} that always returns the result of invoking {@code CharSupplier.getAsChar()} on
     * {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToCharFunction<Object> forSupplier(@Nonnull final CharSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsChar();
    }

    /**
     * Creates a {@link ToDoubleFunction} that always returns the result of invoking {@link
     * DoubleSupplier#getAsDouble()} on {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToDoubleFunction} that always returns the result of invoking {@code
     * DoubleSupplier.getAsDouble()} on {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToDoubleFunction<Object> forSupplier(@Nonnull final DoubleSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsDouble();
    }

    /**
     * Creates a {@link ToFloatFunction} that always returns the result of invoking {@link FloatSupplier#getAsFloat()}
     * on {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToFloatFunction} that always returns the result of invoking {@code FloatSupplier.getAsFloat()}
     * on {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToFloatFunction<Object> forSupplier(@Nonnull final FloatSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsFloat();
    }

    /**
     * Creates a {@link ToIntFunction} that always returns the result of invoking {@link IntSupplier#getAsInt()} on
     * {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToIntFunction} that always returns the result of invoking {@code IntSupplier.getAsInt()} on
     * {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToIntFunction<Object> forSupplier(@Nonnull final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsInt();
    }

    /**
     * Creates a {@link ToLongFunction} that always returns the result of invoking {@link LongSupplier#getAsLong()} on
     * {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToLongFunction} that always returns the result of invoking {@code LongSupplier.getAsLong()} on
     * {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToLongFunction<Object> forSupplier(@Nonnull final LongSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsLong();
    }

    /**
     * Creates a {@link ToShortFunction} that always returns the result of invoking {@link ShortSupplier#getAsShort()}
     * on {@code supplier}, regardless of its input.
     *
     * @param supplier The supplier to be invoked
     * @return A {@code ToShortFunction} that always returns the result of invoking {@code ShortSupplier.getAsShort()}
     * on {@code supplier} regardless of its input.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static ToShortFunction<Object> forSupplier(@Nonnull final ShortSupplier supplier) {
        Objects.requireNonNull(supplier);
        return ignored -> supplier.getAsShort();
    }

    /**
     * Returns a {@link Function} that calls {@code toString()} on its argument.
     *
     * @return A function that calls {@code toString()} on its argument.
     * @implNote This function does not accept {@code null} values; it will throw {@link NullPointerException} when
     * applied to {@code null}.
     * @see Object#toString()
     */
    public static Function<Object, String> toStringFunction() {
        return o -> {
            Objects.requireNonNull(o);
            return o.toString();
        };
    }
}
