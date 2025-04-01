package com.interact.page.notes;

import com.interact.page.BasePage;
import lombok.Getter;

@Getter
public abstract class BasePageNote<T extends BasePageNote<T>> extends BasePage<BasePageNote<T>> {
}
