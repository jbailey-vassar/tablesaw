/*
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

package tech.tablesaw.columns.numbers.filters;

import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.ColumnReference;
import tech.tablesaw.filtering.ColumnFilter;
import tech.tablesaw.selection.Selection;

public class BetweenExclusive extends ColumnFilter {

    private final double low;
    private final double high;

    public BetweenExclusive(ColumnReference reference, Number lowValue, Number highValue) {
        super(reference);
        this.low = lowValue.doubleValue();
        this.high = highValue.doubleValue();
    }

    public Selection apply(Table relation) {
        return apply(relation.column(columnReference().getColumnName()));
    }

    public Selection apply(Column columnBeingFiltered) {
        NumberColumn numberColumn = (NumberColumn) columnBeingFiltered;
        return numberColumn.isBetweenExclusive(low, high);
    }
}
