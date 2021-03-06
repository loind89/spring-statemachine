/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.statemachine.config.configurers;

import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.transition.Transition;

/**
 * Base {@code TransitionConfigurer} interface for configuring {@link Transition}s.
 *
 * @author Janne Valkealahti
 *
 * @param <T> the type of a transition configurer
 * @param <S> the type of state
 * @param <E> the type of event
 */
public interface TransitionConfigurer<T, S, E> extends
		AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<S, E>> {

	/**
	 * Specify a source state {@code S} for this {@link Transition}.
	 *
	 * @param source the source state {@code S}
	 * @return configurer for chaining
	 */
	T source(S source);

	T state(S state);

	/**
	 * Specify event {@code E} for this {@link Transition}.
	 *
	 * @param event the event for transition
	 * @return configurer for chaining
	 */
	T event(E event);

	/**
	 * Specify {@link Action} for this {@link Transition}.
	 *
	 * @param action the action
	 * @return configurer for chaining
	 */
	T action(Action<S, E> action);

	/**
	 * Specify a {@link Guard} for this {@link Transition}.
	 *
	 * @param guard the guard
	 * @return configurer for chaining
	 */
	T guard(Guard<S, E> guard);

	/**
	 * Specify a {@link Guard} backed by a SpEL expression for this {@link Transition}.
	 *
	 * @param expression the SpEL expression
	 * @return configurer for chaining
	 */
	T guardExpression(String expression);

}
