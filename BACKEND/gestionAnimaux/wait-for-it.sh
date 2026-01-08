#!/bin/bash
# wait-for-it.sh

set -e

host="$1"
shift
cmd="$@"

host_name=$(echo $host | cut -d ":" -f1)
host_port=$(echo $host | cut -d ":" -f2)

until nc -z $host_name $host_port; do
  >&2 echo "$host is unavailable - sleeping"
  sleep 3
done

>&2 echo "$host is up - executing command"
exec $cmd