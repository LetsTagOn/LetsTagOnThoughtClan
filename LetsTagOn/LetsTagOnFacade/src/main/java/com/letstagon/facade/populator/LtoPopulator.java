package com.letstagon.facade.populator;

// TODO: Auto-generated Javadoc
/**
 * The Interface LtoPopulator.
 *
 * @param <S> the generic type
 * @param <T> the generic type
 */
public interface LtoPopulator<S, T> {

	/**
	 * Populate.
	 *
	 * @param source the source
	 * @param destination the destination
	 * @return the t
	 */
	T populate(S source, T destination);

}