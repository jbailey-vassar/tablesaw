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

package tech.tablesaw.columns.dates.filters;

import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.ColumnReference;
import tech.tablesaw.columns.dates.PackedLocalDate;
import tech.tablesaw.filtering.ColumnFilter;
import tech.tablesaw.selection.Selection;

import javax.annotation.concurrent.Immutable;

@Immutable
public class IsAfter extends ColumnFilter {

    private final int value;

    public IsAfter(ColumnReference reference, int value) {
        super(reference);
        this.value = value;
    }

    @Override
    public Selection apply(Table relation) {
        return apply(relation.column(columnReference().getColumnName()));
    }

    @Override
    public Selection apply(Column columnBeingFiltered) {
        DateColumn dateColumn = (DateColumn) columnBeingFiltered;
        return dateColumn.eval(PackedLocalDate::isAfter, value);
    }
}
