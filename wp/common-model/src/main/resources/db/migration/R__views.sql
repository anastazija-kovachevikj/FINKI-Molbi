Drop VIEW if exists events_view;

CREATE VIEW events_view AS
(
select concat('semester:', code) as id,
       'SEMESTER'                as type,
       code                      as name,
       ''                        as description,
       start_date::timestamp     as start_time,
       end_date::timestamp       as end_time,
       null                      as physical_location_name,
       null                      as online_url,
       code                      as reference_id,
       'Semester'                as reference_class
from semester
union
select concat('semester-enrollment:', code) as id,
       'SEMESTER'                           as type,
       code                                 as name,
       ''                                   as description,
       enrollment_start_date::timestamp     as start_time,
       enrollment_end_date::timestamp       as end_time,
       null                                 as physical_location_name,
       null                                 as online_url,
       code                                 as reference_id,
       'Semester'                           as reference_class
from semester);