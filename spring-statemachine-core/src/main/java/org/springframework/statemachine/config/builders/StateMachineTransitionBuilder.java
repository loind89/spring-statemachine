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
package org.springframework.statemachine.config.builders;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.builders.StateMachineTransitions.TransitionData;
import org.springframework.statemachine.config.common.annotation.AbstractConfiguredAnnotationBuilder;
import org.springframework.statemachine.config.common.annotation.ObjectPostProcessor;
import org.springframework.statemachine.config.configurers.DefaultExternalTransitionConfigurer;
import org.springframework.statemachine.config.configurers.DefaultInternalTransitionConfigurer;
import org.springframework.statemachine.config.configurers.DefaultLocalTransitionConfigurer;
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer;
import org.springframework.statemachine.config.configurers.InternalTransitionConfigurer;
import org.springframework.statemachine.config.configurers.LocalTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.transition.TransitionKind;

public class StateMachineTransitionBuilder<S, E>
		extends
		AbstractConfiguredAnnotationBuilder<StateMachineTransitions<S, E>, StateMachineTransitionConfigurer<S, E>, StateMachineTransitionBuilder<S, E>>
		implements StateMachineTransitionConfigurer<S, E> {

	private Collection<TransitionData<S, E>> transitionData = new ArrayList<TransitionData<S, E>>();

	public StateMachineTransitionBuilder() {
		super();
	}

	public StateMachineTransitionBuilder(ObjectPostProcessor<Object> objectPostProcessor,
			boolean allowConfigurersOfSameType) {
		super(objectPostProcessor, allowConfigurersOfSameType);
	}

	public StateMachineTransitionBuilder(ObjectPostProcessor<Object> objectPostProcessor) {
		super(objectPostProcessor);
	}

	@Override
	protected StateMachineTransitions<S, E> performBuild() throws Exception {
		StateMachineTransitions<S, E> bean = new StateMachineTransitions<S, E>(transitionData);
		return bean;
	}

	@Override
	public ExternalTransitionConfigurer<S, E> withExternal() throws Exception {
		return apply(new DefaultExternalTransitionConfigurer<S, E>());
	}

	@Override
	public InternalTransitionConfigurer<S, E> withInternal() throws Exception {
		return apply(new DefaultInternalTransitionConfigurer<S, E>());
	}

	@Override
	public LocalTransitionConfigurer<S, E> withLocal() throws Exception {
		return apply(new DefaultLocalTransitionConfigurer<S, E>());
	}

	public void add(S source, S target, S state, E event, Collection<Action<S, E>> actions, Guard<S, E> guard, TransitionKind kind) {
		transitionData.add(new TransitionData<S, E>(source, target, state, event, actions, guard, kind));
	}

}
